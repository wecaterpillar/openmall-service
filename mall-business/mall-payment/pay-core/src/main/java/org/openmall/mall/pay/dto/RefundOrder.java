package org.openmall.mall.pay.dto;


import java.math.BigDecimal;
import java.util.Date;

/**
 * 退款订单信息
 */
@lombok.Data
public class RefundOrder {
    /**
     * 退款单号，每次进行退款的单号，此处唯一
     */
    private String refundNo;
    /**
     * 支付平台订单号,交易号
     */
    private String tradeNo;
    /**
     * 商户单号
     */
    private String outTradeNo;
    /**
     * 退款金额
     */
    private BigDecimal refundAmount;
    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;

    /**
     * 退款交易日期
     */
    private Date orderDate;

    /**
     * 货币CurType
     */
    private String curCode;
    private String curName;
    /**
     * 退款说明
     */
    private String description;

}
