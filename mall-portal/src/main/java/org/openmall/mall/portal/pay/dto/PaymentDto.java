package org.openmall.mall.portal.pay.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by qingsong on 2020-03-17
 */
@Data
public class PaymentDto  implements Serializable {
    @ApiModelProperty("订单ID")
    private long orderId;
    @ApiModelProperty("取消跳转URL")
    private String cancelUrl;
    @ApiModelProperty("成功跳转URL")
    private String successUrl;
    @ApiModelProperty("类型： paypal")
    private String type;
    @ApiModelProperty("描述")
    private String description;
    @ApiModelProperty("金额")
    private double total;
    @ApiModelProperty("币种，默认 USD")
    private String currency;

    private static final long serialVersionUID = 1L;
}
