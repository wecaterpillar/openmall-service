package org.openmall.mall.pms.dto;

import org.openmall.mall.pms.model.PmsProductAttribute;
import org.openmall.mall.pms.model.PmsProductAttributeCategory;

import java.util.List;

/**
 * 包含有分类下属性的dto
 */
@lombok.Data
public class PmsProductAttributeCategoryItem extends PmsProductAttributeCategory {
    private List<PmsProductAttribute> productAttributeList;

}
