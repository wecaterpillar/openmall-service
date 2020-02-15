package org.openmall.mall.portal.pms.service;

import org.openmall.mall.pms.dto.PmsProductCategoryWithChildrenItem;
import org.openmall.mall.pms.model.PmsProductAttribute;
import org.openmall.mall.pms.model.PmsProductCategory;

import java.util.List;

/**
 * 商品分类查询
 */
public interface PortalPmsProductAttributeService {
    List<PmsProductAttribute> getList(Long cid);
}
