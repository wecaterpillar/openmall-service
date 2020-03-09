package org.openmall.mall.pms.dto;

import org.openmall.mall.pms.model.PmsProductCategory;

import java.util.List;

/**
 */
@lombok.Data
public class PmsProductCategoryWithChildrenItem extends PmsProductCategory {
    private List<PmsProductCategory> children;

}
