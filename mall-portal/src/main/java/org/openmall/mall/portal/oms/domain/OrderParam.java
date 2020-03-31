package org.openmall.mall.portal.oms.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openmall.mall.oms.model.OmsCartItem;

import java.util.List;

/**
 * 生成订单时传入的参数
 */
@ApiModel("生成订单时传入的参数")
@lombok.Data
public class OrderParam {
    //下单商品（ OmsCartItem 必须字段：商品id/SKUid/数量）
    private List<OmsCartItem> carItems;

    //收货地址id
    @ApiModelProperty("收货地址id")
    private Long memberReceiveAddressId;
    //优惠券id
    @ApiModelProperty("优惠券id")
    private Long couponId;
    //使用的积分数
    @ApiModelProperty("使用的积分数")
    private Integer useIntegration;
    //支付方式
    @ApiModelProperty("优先选择支付方式")
    private Integer payType;

}
