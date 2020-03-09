package org.openmall.mall.pms.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品满减
 *
 * 商品优惠相关表，购买同商品满足一定金额后，可以减免一定金额。如：买满1000减100元。
 */
@lombok.Data
public class PmsProductFullReduction implements Serializable {
    private Long id;

    @ApiModelProperty(value = "商品id")
    private Long productId;

    @ApiModelProperty(value = "商品满足金额")
    private BigDecimal fullPrice;

    @ApiModelProperty(value = "商品减少金额")
    private BigDecimal reducePrice;

    private static final long serialVersionUID = 1L;

}
