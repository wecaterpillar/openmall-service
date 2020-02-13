package org.openmall.mall.portal.pms.service;

import org.openmall.mall.pms.dto.PmsProductCategoryWithChildrenItem;
import org.openmall.mall.pms.model.PmsProductCategory;

import java.util.List;

/**
 * 商品分类查询
 */
public interface PortalPmsProductCategoryService {
    PmsProductCategory getCategoryById(long id);
    List<PmsProductCategory> getList(Long parentId, Integer pageSize, Integer pageNum);
    List<PmsProductCategoryWithChildrenItem> listWithChildren();
}
