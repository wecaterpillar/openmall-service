package org.openmall.mall.sms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * 优惠券和商品的关系
 *
 * 用于存储优惠券与商品的关系，当优惠券的使用类型为指定商品时，优惠券与商品需要建立关系。
 */
@lombok.Data
public class SmsCouponProductRelation implements Serializable {
    private Long id;

    @ApiModelProperty(value = "优惠券id")
    private Long couponId;

    private Long productId;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品编码")
    private String productSn;

    private static final long serialVersionUID = 1L;


}
