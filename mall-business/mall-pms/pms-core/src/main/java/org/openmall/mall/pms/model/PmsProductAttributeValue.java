package org.openmall.mall.pms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * 商品属性值
 *
 * 如果对应的参数是规格且规格支持手动添加，那么该表用于存储手动新增的值；
 * 如果对应的商品属性是参数，那么该表用于存储参数的值。
 */
@lombok.Data
public class PmsProductAttributeValue implements Serializable {
    private Long id;

    @ApiModelProperty(value = "商品id")
    private Long productId;

    @ApiModelProperty(value = "商品属性id")
    private Long productAttributeId;

    @ApiModelProperty(value = "手动添加规格或参数的值，参数单值，规格有多个时以逗号隔开")
    private String value;

    @ApiModelProperty(value = "商品属性名称")
    private String name;

    private static final long serialVersionUID = 1L;

    public PmsProductAttributeValue(){
        int k=0;
        k++;
    }
}
