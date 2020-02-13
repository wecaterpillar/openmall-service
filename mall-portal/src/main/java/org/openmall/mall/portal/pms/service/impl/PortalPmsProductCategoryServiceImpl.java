package org.openmall.mall.portal.pms.service.impl;

import com.github.pagehelper.PageHelper;
import org.openmall.mall.pms.dao.PmsProductCategoryDao;
import org.openmall.mall.pms.dto.PmsProductCategoryWithChildrenItem;
import org.openmall.mall.pms.mapper.PmsProductCategoryMapper;
import org.openmall.mall.pms.model.PmsProductCategory;
import org.openmall.mall.pms.model.PmsProductCategoryExample;
import org.openmall.mall.portal.pms.service.PortalPmsProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品分类查询
 */
@Service
public class PortalPmsProductCategoryServiceImpl implements PortalPmsProductCategoryService {
    @Autowired
    PmsProductCategoryMapper productCategoryMapper;

    @Autowired
    PmsProductCategoryDao productCategoryDao;

    @Override
    public PmsProductCategory getCategoryById(long id) {
        return null;
    }

    public List<PmsProductCategory> getList(Long parentId, Integer pageSize, Integer pageNum){
        PageHelper.startPage(pageNum, pageSize);
        PmsProductCategoryExample example = new PmsProductCategoryExample();
        example.setOrderByClause("sort desc");
        example.createCriteria().andParentIdEqualTo(parentId);
        return productCategoryMapper.selectByExample(example);
    }

    public List<PmsProductCategoryWithChildrenItem> listWithChildren() {
        return productCategoryDao.listWithChildren();
    }
}
