package org.openmall.mall.pay.dto;

import com.egzosn.pay.common.util.str.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * 商户支付订单
 */
@lombok.Data
public class PayOrder {

    /**
     * 商品名称
     */
    private String subject;
    /**
     * 商品描述
     */
    private String body;
    /**
     * 附加信息
     */
    private String addition;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 支付平台订单号,交易号
     */
    private String tradeNo;
    /**
     * 商户订单号
     */
    private String outTradeNo;
    /**
     * 银行卡类型
     */
    private String bankType;
    /**
     * 设备信息
     */
    private String deviceInfo;
    /**
     * 支付创建ip
     */
    private String spbillCreateIp;
    /**
     * 付款条码串,人脸凭证，有关支付代码相关的，
     */
    private String authCode;
    /**
     * 微信专用，，，，
     * WAP支付链接
     */
    private String wapUrl;
    /**
     * 微信专用，，，，
     * WAP支付网页名称
     */

    private String wapName;
    /**
     * 用户唯一标识
     * 微信含 sub_openid 字段
     */
    private String openid;
    /**
     * 交易类型TransactionType
     */
    private String transactionType;
    private String transactionMethod;

    /**
     * 支付币种CurType
     */
    private String curCode;
    private String curName;
    /**
     * 订单过期时间
     */
    private Date expirationTime;

    /**
     * 订单附加信息，可用于预设未提供的参数，这里会覆盖以上所有的订单信息，
     */
    private Map<String, Object> attr;

    /**
     * 列表id
     */
    private String detailsId;
    /**
     * 交易类型，交易方式，
     * 本字段与{@com.egzosn.pay.common.bean.PayOrder#transactionType}相同。
     *
     *  例如，网页支付，扫码付等等
     */
    private String wayTrade;


    public PayOrder(String detailsId, String wayTrade, String subject, String body, BigDecimal price, String outTradeNo) {
        this.subject = StringUtils.tryTrim(subject);
        this.body = StringUtils.tryTrim(body);
        this.price = price;
        this.outTradeNo = StringUtils.tryTrim(outTradeNo);
        //this.transactionType = transactionType;
        this.detailsId = detailsId;
        this.wayTrade = wayTrade;
    }
}
