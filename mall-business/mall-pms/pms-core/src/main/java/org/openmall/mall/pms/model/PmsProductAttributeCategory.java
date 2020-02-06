package org.openmall.mall.pms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * 商品属性分类
 */
@lombok.Data
public class PmsProductAttributeCategory implements Serializable {
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "属性数量")
    private Integer attributeCount;

    @ApiModelProperty(value = "参数数量")
    private Integer paramCount;

    private static final long serialVersionUID = 1L;

}
