package org.openmall.mall.oms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * 订单设置
 */
@lombok.Data
public class OmsOrderSetting implements Serializable {
    private Long id;

    @ApiModelProperty(value = "秒杀订单超时关闭时间(分)")
    private Integer flashOrderOvertime;

    @ApiModelProperty(value = "正常订单超时时间(分)")
    private Integer normalOrderOvertime;

    @ApiModelProperty(value = "发货后自动确认收货时间（天）")
    private Integer confirmOvertime;

    @ApiModelProperty(value = "自动完成交易时间，不能申请售后（天）")
    private Integer finishOvertime;

    @ApiModelProperty(value = "订单完成后自动好评时间（天）")
    private Integer commentOvertime;

    private static final long serialVersionUID = 1L;
}
