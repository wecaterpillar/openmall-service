package org.openmall.mall.pay.dto;

import java.io.Serializable;
import java.util.Map;

/**
 * 支付回调消息
 * 基础实现，具体可根据具体支付回调的消息去实现
 */
@lombok.Data
public class PayMessage implements Serializable {

    private Map<String, Object> payMessage = null;

    private String msgType;
    private String payType;
    private String transactionType;
    private String fromPay;
    private String describe;

}
