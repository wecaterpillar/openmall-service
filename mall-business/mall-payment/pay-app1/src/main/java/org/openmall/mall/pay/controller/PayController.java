package org.openmall.mall.pay.controller;

import org.openmall.mall.pay.dto.PayOrder;
import org.openmall.mall.pay.service.MallPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;

/**
 */

@RequestMapping("pay")
@Controller
public class PayController {

    @Autowired
    private MallPayService payService;


    /**
     * 网页支付
     * @param detailsId 列表id
     * @param wayTrade 交易方式
     * @return 网页
     */
    @ResponseBody
    @RequestMapping(value = "toPay.html", produces = "text/html;charset=UTF-8")
    public String toPay(String detailsId, String wayTrade, BigDecimal price) {
        PayOrder payOrder = new PayOrder(detailsId, wayTrade, "订单title", "摘要", null == price ? new BigDecimal(0.01) : price, UUID.randomUUID().toString().replace("-", ""));
        return payService.toPay(payOrder);
    }
    /**
     * 二维码
     * @param detailsId 列表id
     * @param wayTrade 交易方式
     * @return 二维码
     */
    @ResponseBody
    @RequestMapping(value = "toQrPay.jpg", produces = "image/jpeg;charset=UTF-8")
    public byte[] toQrPay(String detailsId, String wayTrade, BigDecimal price) throws IOException {
        PayOrder payOrder = new PayOrder(detailsId, wayTrade, "订单title", "摘要", null == price ? new BigDecimal(0.01) : price, UUID.randomUUID().toString().replace("-", ""));
        return payService.toQrPay(payOrder);
    }
    /**
     * 二维码信息
     * @param detailsId 列表id
     * @param wayTrade 交易方式
     * @return 二维码信息
     */
    @ResponseBody
    @RequestMapping(value = "getQrPay.json")
    public String getQrPay(String detailsId, String wayTrade, BigDecimal price) throws IOException {
        PayOrder payOrder = new PayOrder(detailsId, wayTrade, "订单title", "摘要", null == price ? new BigDecimal(0.01) : price, UUID.randomUUID().toString().replace("-", ""));
        return payService.getQrPay(payOrder);
    }


    /**
     * 支付回调地址
     * @param request 请求
     * @param detailsId 列表id
     * @return 支付是否成功
     * @throws IOException IOException
     */
    @RequestMapping(value = "payBack{detailsId}.json")
    public String payBack(HttpServletRequest request, @PathVariable String detailsId) throws IOException {
        //业务处理在对应的PayMessageHandler里面处理，在哪里设置PayMessageHandler，详情查看com.egzosn.pay.common.api.PayService.setPayMessageHandler()
        return payService.payBack(detailsId, request.getParameterMap(), request.getInputStream());
    }

}
