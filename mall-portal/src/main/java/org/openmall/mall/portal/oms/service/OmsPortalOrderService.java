package org.openmall.mall.portal.oms.service;

import org.openmall.mall.portal.oms.domain.ConfirmOrderResult;
import org.openmall.mall.portal.oms.domain.OrderParam;
import org.openmall.mall.portal.pay.dto.PaymentDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 前台订单管理Service
 */
public interface OmsPortalOrderService {
    /**
     * 根据用户购物车信息生成确认单信息
     */
    ConfirmOrderResult generateConfirmOrder();

    /**
     * 根据所选商品列表生成确认单信息
     */
    ConfirmOrderResult generateConfirmOrder(OrderParam orderParam);

    /**
     * 根据提交信息生成订单
     */
    @Transactional
    Map<String, Object> generateOrder(OrderParam orderParam);

    /**
     * @param PaymentDto
     * @return PAY URL
     */
    String createPay(PaymentDto paymentDto);

    /**
     * 支付成功后的回调
     */
    @Transactional
    Integer paySuccess(Long orderId);

    /**
     * 自动取消超时订单
     */
    @Transactional
    Integer cancelTimeOutOrder();

    /**
     * 取消单个超时订单
     */
    @Transactional
    void cancelOrder(Long orderId);

    /**
     * 发送延迟消息取消订单
     */
    void sendDelayMessageCancelOrder(Long orderId);
}
