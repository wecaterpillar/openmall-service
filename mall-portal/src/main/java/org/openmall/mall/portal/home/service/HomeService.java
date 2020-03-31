package org.openmall.mall.portal.home.service;

import org.openmall.mall.cms.model.CmsSubject;
import org.openmall.mall.pms.model.PmsProduct;
import org.openmall.mall.pms.model.PmsProductCategory;
import org.openmall.mall.portal.home.domain.HomeContentResult;

import java.util.List;
import java.util.Map;

/**
 * 首页内容管理Service
 */
public interface HomeService {

    /**
     * 获取当前网站配置信息
     * @return
     */
    Map<String, Object> getChannelConfig();

    /**
     * 获取首页内容
     */
    HomeContentResult content();

    /**
     * 首页商品推荐
     */
    List<PmsProduct> recommendProductList(Integer pageSize, Integer pageNum);

    /**
     * 获取商品分类
     * @param parentId 0:获取一级分类；其他：获取指定二级分类
     */
    List<PmsProductCategory> getProductCateList(Long parentId);

    /**
     * 根据专题分类分页获取专题
     * @param cateId 专题分类id
     */
    List<CmsSubject> getSubjectList(Long cateId, Integer pageSize, Integer pageNum);
}
