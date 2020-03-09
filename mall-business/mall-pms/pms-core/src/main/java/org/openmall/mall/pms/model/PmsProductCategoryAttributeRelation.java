package org.openmall.mall.pms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * 商品分类和属性的关系
 *
 * 用于选中分类后搜索时生成筛选属性。
 */
@lombok.Data
public class PmsProductCategoryAttributeRelation implements Serializable {
    private Long id;

    @ApiModelProperty(value = "商品分类id")
    private Long productCategoryId;

    @ApiModelProperty(value = "商品属性id")
    private Long productAttributeId;

    private static final long serialVersionUID = 1L;


}
