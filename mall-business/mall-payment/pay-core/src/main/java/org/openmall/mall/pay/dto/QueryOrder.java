package org.openmall.mall.pay.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单辅助接口
 */
@lombok.Data
public class QueryOrder {
    /**
     * 列表id
     */
    private String detailsId;
    /**
     * 支付平台订单号
      */
    private String tradeNo;

    /**
     * 商户单号
     */
    private String outTradeNo;
    /**
     *     退款金额
     */
    private BigDecimal refundAmount;
    /**
     *     总金额
     */
    private BigDecimal totalAmount;
    /**
     *     账单时间：具体请查看对应支付平台
     */
    private Date billDate;
    /**
     *    账单时间：具体请查看对应支付平台
     */
    private String billType;
    /**
     *     支付平台订单号或者账单日期
     */
    private Object tradeNoOrBillDate;
    /**
     * 商户单号或者 账单类型
      */
    private String outTradeNoBillType;
    /**
     * 交易类型，交易方式，
     * 本字段与{@com.egzosn.pay.common.bean.PayOrder#transactionType}相同。
     *
     *  例如，网页支付，扫码付等等
     */
    private String wayTrade;


}
