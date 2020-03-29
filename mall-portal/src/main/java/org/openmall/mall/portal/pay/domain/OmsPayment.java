package org.openmall.mall.portal.pay.domain;

import lombok.Data;

import java.util.Date;

/**
 *
 * 支付记录
 *
 *
 * Created by qingsong on 2020-03-17
 */
@Data
public class OmsPayment {
    // 主键
    private long id;

    // 订单ID， 一个订单可能包含多笔付款
    private String orderId; // 订单ID， 一个订单可能包含多笔付款
    private String orderSn; // 订单编号

    // 付款类型 1 正常付款， 2 定金， 3 尾款
    private String payType;

    // 付款方式， "paypal", "wechat"
    private String payedBy;

    // 付款ID
    private String payId;

    // 付款状态, created/approved/failed
    private String payState;

    // 金额
    private double payAmount;
    // 币种
    private String apyCurrency;

    // 描述
    private String payDesc;

    private String  payDetails;

    // 创建人
    private String createdBy;
    // 更新人
    private String updatedBy;
    // 创建时间
    private Date createTime;
    // 更新时间
    private Date updateTime;
}
