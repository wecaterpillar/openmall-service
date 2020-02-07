package org.openmall.mall.sms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * 优惠券历史记录
 *
 * 用于存储会员领取及使用优惠券的记录，当会员领取到优惠券时，会产生一条优惠券的记录，需要注意的是它的使用状态：0->未使用；1->已使用；2->已过期。
 */
@lombok.Data
public class SmsCouponHistory implements Serializable {
    private Long id;

    @ApiModelProperty(value = "优惠券id")
    private Long couponId;

    @ApiModelProperty(value = "会员id")
    private Long memberId;

    @ApiModelProperty(value = "优惠券码")
    private String couponCode;

    @ApiModelProperty(value = "领取人昵称")
    private String memberNickname;

    @ApiModelProperty(value = "获取类型：0->后台赠送；1->主动获取")
    private Integer getType;

    private Date createTime;

    @ApiModelProperty(value = "使用状态：0->未使用；1->已使用；2->已过期")
    private Integer useStatus;

    @ApiModelProperty(value = "使用时间")
    private Date useTime;

    @ApiModelProperty(value = "订单编号")
    private Long orderId;

    @ApiModelProperty(value = "订单号码")
    private String orderSn;

    private static final long serialVersionUID = 1L;


}
