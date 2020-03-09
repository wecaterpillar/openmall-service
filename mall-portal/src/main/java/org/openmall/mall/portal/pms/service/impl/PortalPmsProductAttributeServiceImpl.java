package org.openmall.mall.portal.pms.service.impl;

import com.github.pagehelper.PageHelper;
import org.openmall.mall.pms.dao.PmsProductAttributeDao;
import org.openmall.mall.pms.dao.PmsProductCategoryDao;
import org.openmall.mall.pms.dto.PmsProductCategoryWithChildrenItem;
import org.openmall.mall.pms.mapper.PmsProductAttributeMapper;
import org.openmall.mall.pms.mapper.PmsProductCategoryMapper;
import org.openmall.mall.pms.model.PmsProductAttribute;
import org.openmall.mall.pms.model.PmsProductAttributeExample;
import org.openmall.mall.pms.model.PmsProductCategory;
import org.openmall.mall.pms.model.PmsProductCategoryExample;
import org.openmall.mall.portal.pms.service.PortalPmsProductAttributeService;
import org.openmall.mall.portal.pms.service.PortalPmsProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品分类查询
 */
@Service
public class PortalPmsProductAttributeServiceImpl implements PortalPmsProductAttributeService {
    @Autowired
    PmsProductAttributeMapper productAttributeMapper;

    public List<PmsProductAttribute> getList(Long cid) {
        PmsProductAttributeExample example = new PmsProductAttributeExample();
        example.setOrderByClause("sort desc");
        example.createCriteria().andProductAttributeCategoryIdEqualTo(cid);
        return productAttributeMapper.selectByExample(example);
    }
}
