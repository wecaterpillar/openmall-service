package org.openmall.mall.pms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品阶梯价格
 *
 * 商品优惠相关表，购买同商品满足一定数量后，可以使用打折价格进行购买。如：买两件商品可以打八折。
 */
@lombok.Data
public class PmsProductLadder implements Serializable {
    private Long id;

    @ApiModelProperty(value = "商品id")
    private Long productId;

    @ApiModelProperty(value = "满足的商品数量")
    private Integer count;

    @ApiModelProperty(value = "折扣")
    private BigDecimal discount;

    @ApiModelProperty(value = "折后价格")
    private BigDecimal price;

    private static final long serialVersionUID = 1L;

}
