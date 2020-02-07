package org.openmall.mall.sms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * 优惠券和商品分类关系
 *
 * 用于存储优惠券与商品分类的关系，当优惠券的使用类型为指定分类时，优惠券与商品分类需要建立关系。
 */
@lombok.Data
public class SmsCouponProductCategoryRelation implements Serializable {
    private Long id;

    @ApiModelProperty(value = "优惠券id")
    private Long couponId;

    private Long productCategoryId;

    @ApiModelProperty(value = "产品分类名称")
    private String productCategoryName;

    @ApiModelProperty(value = "父分类名称")
    private String parentCategoryName;

    private static final long serialVersionUID = 1L;

}
