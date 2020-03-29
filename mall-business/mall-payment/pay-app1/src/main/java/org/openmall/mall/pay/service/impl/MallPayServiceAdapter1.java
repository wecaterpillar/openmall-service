package org.openmall.mall.pay.service.impl;

import com.egzosn.pay.common.api.PayMessageInterceptor;
import com.egzosn.pay.spring.boot.core.MerchantPayServiceManager;
import org.openmall.mall.pay.dto.*;
import org.openmall.mall.pay.service.MallPayService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Service
public class MallPayServiceAdapter1 implements MallPayService
{

    @Autowired
    private MerchantPayServiceManager payServiceManager;

    /**
     * 跳到支付页面
     * 针对实时支付,即时付款
     *
     * @param payOrder 商户支付订单信息
     * @return 跳到支付页面
     */
    public String toPay(PayOrder payOrder) {
        return payServiceManager.toPay(convert(payOrder));
    }

    /**
     * 获取支付预订单信息
     *
     * @param payOrder 商户支付订单信息
     * @return 支付预订单信息
     */
    public Map<String, Object> getOrderInfo(PayOrder payOrder) {
        return payServiceManager.getOrderInfo(convert(payOrder));
    }

    /**
     * 刷卡付,pos主动扫码付款(条码付)
     * 刷脸付
     *
     * @param payOrder 商户支付订单信息
     * @return 支付结果
     */
    public Map<String, Object> microPay(PayOrder payOrder) {
        return payServiceManager.microPay(convert(payOrder));
    }

    /**
     * 获取二维码图像
     * 二维码支付
     *
     * @param payOrder 商户支付订单信息
     * @return 二维码图像
     * @throws IOException IOException
     */
    public byte[] toQrPay(PayOrder payOrder) throws IOException {
        return payServiceManager.toQrPay(convert(payOrder));
    }

    /**
     * 获取二维码信息
     * 二维码支付
     *
     * @param payOrder 商户支付订单信息
     * @return 二维码信息
     */
    public String getQrPay(PayOrder payOrder) {
        return payServiceManager.getQrPay(convert(payOrder));
    }

    /**
     * 支付回调地址
     * 方式二
     *
     * @param detailsId    商户列表id
     * @param parameterMap 请求参数
     * @param is           请求流
     * @return 支付是否成功
     * @throws IOException IOException
     *                     拦截器相关增加， 详情查看{@link com.egzosn.pay.common.api.PayService#addPayMessageInterceptor(PayMessageInterceptor)}
     *                     <p>
     *                     业务处理在对应的PayMessageHandler里面处理，在哪里设置PayMessageHandler，详情查看{@link com.egzosn.pay.common.api.PayService#setPayMessageHandler(com.egzosn.pay.common.api.PayMessageHandler)}
     *                     </p>
     *                     如果未设置 {@link com.egzosn.pay.common.api.PayMessageHandler} 那么会使用默认的 {@link com.egzosn.pay.common.api.DefaultPayMessageHandler}
     */
    public String payBack(String detailsId, Map<String, String[]> parameterMap, InputStream is) throws IOException {
        return payServiceManager.payBack(detailsId, parameterMap, is);
    }

    /**
     * 查询
     *
     * @param order 订单的请求体
     * @return 返回查询回来的结果集，支付方原值返回
     */
    public Map<String, Object> query(QueryOrder order) {
        return payServiceManager.query(convert(order));
    }

    /**
     * 交易关闭接口
     *
     * @param order 订单的请求体
     * @return 返回支付方交易关闭后的结果
     */
    public Map<String, Object> close(QueryOrder order) {
        return payServiceManager.close(convert(order));
    }

    /**
     * 申请退款接口
     *
     * @param detailsId 列表id
     * @param order     订单的请求体
     * @return 返回支付方申请退款后的结果
     */
    public Map<String, Object> refund(String detailsId, RefundOrder order) {
        return payServiceManager.refund(detailsId, convert(order));
    }

    /**
     * 查询退款
     *
     * @param detailsId 列表id
     * @param order     订单的请求体
     * @return 返回支付方查询退款后的结果
     */
    public Map<String, Object> refundquery(String detailsId, RefundOrder order) {
        return payServiceManager.refundquery(detailsId, convert(order));
    }

    /**
     * 下载对账单
     *
     * @param order 订单的请求体
     * @return 返回支付方下载对账单的结果
     */
    public Map<String, Object> downloadbill(QueryOrder order) {
        return payServiceManager.downloadbill(convert(order));
    }

    /**
     * 通用查询接口，根据 TransactionType 类型进行实现,此接口不包括退款
     *
     * @param order 订单的请求体
     * @return 返回支付方对应接口的结果
     */
    public Map<String, Object> secondaryInterface(QueryOrder order) {
        return payServiceManager.secondaryInterface(convert(order));
    }

    /**
     * 转账
     *
     * @param detailsId 列表id
     * @param order     转账订单
     * @return 对应的转账结果
     */
    public Map<String, Object> transfer(String detailsId, TransferOrder order) {
        return payServiceManager.transfer(detailsId, convert(order));
    }

    /**
     * 转账查询
     *
     * @param detailsId 列表id
     * @param outNo     商户转账订单号
     * @param tradeNo   支付平台转账订单号
     * @return 对应的转账订单
     */
    public Map<String, Object> transferQuery(String detailsId, String outNo, String tradeNo) {
        return payServiceManager.transferQuery(detailsId, outNo, tradeNo);
    }

    /**
     * 创建消息
     *
     * @param detailsId 列表id
     * @param message   支付平台返回的消息
     * @return 支付消息对象
     */
    public PayMessage createMessage(String detailsId, Map<String, Object> message) {
        return conver(payServiceManager.createMessage(detailsId, message));
    }


    ////////////////////////


    static com.egzosn.pay.spring.boot.core.bean.MerchantPayOrder convert(PayOrder payOrder){
        com.egzosn.pay.spring.boot.core.bean.MerchantPayOrder payOrder1 = new com.egzosn.pay.spring.boot.core.bean.MerchantPayOrder();
        copyProperties(payOrder, payOrder1);
        return payOrder1;
    }

    static com.egzosn.pay.spring.boot.core.bean.MerchantQueryOrder convert(QueryOrder queryOrder){
        com.egzosn.pay.spring.boot.core.bean.MerchantQueryOrder queryOrder1 = new com.egzosn.pay.spring.boot.core.bean.MerchantQueryOrder();
        copyProperties(queryOrder, queryOrder1);
        return queryOrder1;
    }

    static com.egzosn.pay.common.bean.RefundOrder convert(RefundOrder order){
        com.egzosn.pay.common.bean.RefundOrder order1 = new com.egzosn.pay.common.bean.RefundOrder();
        copyProperties(order, order1);
        return order1;
    }

    static com.egzosn.pay.common.bean.TransferOrder convert(TransferOrder order){
        com.egzosn.pay.common.bean.TransferOrder order1 = new com.egzosn.pay.common.bean.TransferOrder();
        copyProperties(order, order1);
        return order1;
    }

    static PayMessage conver(com.egzosn.pay.common.bean.PayMessage message){
        PayMessage message1 = new PayMessage();
        copyProperties(message, message1);
        return message1;
    }

    static void copyProperties(Object source, Object target){
        BeanUtils.copyProperties(source, target);
        //TODO curType, CountryCode, TransferType, TransactionType, Bank
    }
}
