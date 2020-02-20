package org.openmall.mall.portal.pms.service.impl;

import com.github.pagehelper.PageHelper;
import org.openmall.mall.pms.dao.PmsProductDao;
import org.openmall.mall.pms.dto.PmsProductQueryParam;
import org.openmall.mall.pms.dto.PmsProductResult;
import org.openmall.mall.pms.mapper.PmsProductCategoryMapper;
import org.openmall.mall.pms.mapper.PmsProductMapper;
import org.openmall.mall.pms.model.PmsProduct;
import org.openmall.mall.pms.model.PmsProductCategory;
import org.openmall.mall.pms.model.PmsProductExample;
import org.openmall.mall.portal.pms.service.PortalPmsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 商品查询
 */
@Service
public class PortalPmsProductServiceImpl implements PortalPmsProductService{
    private static final String FIELD_PRICE = "price";
    @Autowired
    private PmsProductMapper productMapper;

    @Autowired
    private PmsProductCategoryMapper productCategoryMapper;

    @Autowired
    private PmsProductDao productDao;

    @Override
    public List<PmsProduct> list(String keyword) {
        PmsProductExample productExample = new PmsProductExample();
        PmsProductExample.Criteria criteria = productExample.createCriteria();
        criteria.andDeleteStatusEqualTo(0);
        if(!StringUtils.isEmpty(keyword)){
            criteria.andNameLike("%" + keyword + "%");
            productExample.or().andDeleteStatusEqualTo(0).andProductSnLike("%" + keyword + "%");
        }
        return productMapper.selectByExample(productExample);
    }

    @Override
    public List<PmsProduct> list(PmsProductQueryParam productQueryParam, String sortField, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        PmsProductExample productExample = new PmsProductExample();
        PmsProductExample.Criteria criteria = productExample.createCriteria();
        criteria.andDeleteStatusEqualTo(0);

        // published only
        criteria.andPublishStatusEqualTo(1);

        if (productQueryParam.getVerifyStatus() != null) {
            criteria.andVerifyStatusEqualTo(productQueryParam.getVerifyStatus());
        }
        if (!StringUtils.isEmpty(productQueryParam.getKeyword())) {
            criteria.andNameLike("%" + productQueryParam.getKeyword() + "%");
        }
        if (!StringUtils.isEmpty(productQueryParam.getProductSn())) {
            criteria.andProductSnEqualTo(productQueryParam.getProductSn());
        }
        if (productQueryParam.getBrandId() != null) {
            criteria.andBrandIdEqualTo(productQueryParam.getBrandId());
        }
        if (productQueryParam.getProductCategoryId() != null) {
            PmsProductCategory category = productCategoryMapper.selectByPrimaryKey(productQueryParam.getProductCategoryId());
            if(null!=category) {
                criteria.andProductTreeIdLike(category.getTreeId());
            }
        }


        if((null!=sortField) && (sortField.length()>1))  {
            char sortType = sortField.charAt(0);
            if('+'==sortType ||'-'==sortType){
                sortField=sortField.substring(1);
            }

            if(FIELD_PRICE.equals(sortField)) {
                productExample.setOrderByClause("price " + (sortType=='-'?"desc":""));
            }
    }
        return productMapper.selectByExample(productExample);
    }

    @Override
    public PmsProductResult getProductById(Long id) {
        return productDao.getUpdateInfo(id);
    }
}
