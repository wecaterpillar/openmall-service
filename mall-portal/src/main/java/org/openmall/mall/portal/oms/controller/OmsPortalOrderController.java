package org.openmall.mall.portal.oms.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.openmall.mall.common.api.CommonResult;
import org.openmall.mall.oms.dto.OmsOrderDetail;
import org.openmall.mall.oms.dto.OmsOrderQueryParam;
import org.openmall.mall.oms.model.OmsOrder;
import org.openmall.mall.oms.service.OmsOrderService;
import org.openmall.mall.portal.oms.domain.ConfirmOrderResult;
import org.openmall.mall.portal.oms.domain.OrderParam;
import org.openmall.mall.portal.oms.service.OmsPortalOrderService;
import org.openmall.mall.portal.pay.dto.PaymentDto;
import org.openmall.mall.ums.util.MemberSecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 订单管理Controller
 *
 * TODO:
 * 1. 购物车生成订单时可选择部分商品下单
 * 2. 直接选择某商品生成订单
 */
@RestController
@Api(tags = "OmsPortalOrderController",description = "订单管理")
@RequestMapping("/order")
public class OmsPortalOrderController {


    @Autowired
    private OmsPortalOrderService portalOrderService;

    @Autowired
    private OmsOrderService orderService;


    @ApiOperation("根据购物车信息生成确认单信息")
    @RequestMapping(value = "/generateConfirmOrder",method = RequestMethod.POST)
    public CommonResult<ConfirmOrderResult> generateConfirmOrder(){
        ConfirmOrderResult confirmOrderResult = portalOrderService.generateConfirmOrder();
        return CommonResult.success(confirmOrderResult);
    }
    @ApiOperation("根据购物车信息生成订单")
    @RequestMapping(value = "/generateOrder",method = RequestMethod.POST)
    public CommonResult generateOrder(@RequestBody OrderParam orderParam){
        Map<String, Object> result = portalOrderService.generateOrder(orderParam);
        return CommonResult.success(result, "下单成功");
    }
    @ApiOperation("支付成功的回调")
    @RequestMapping(value = "/paySuccess",method = RequestMethod.POST)
    public CommonResult paySuccess(@RequestParam Long orderId){
        Integer count = portalOrderService.paySuccess(orderId);
        return CommonResult.success(count, "支付成功");
    }

    @ApiOperation("自动取消超时订单")
    @RequestMapping(value = "/cancelTimeOutOrder",method = RequestMethod.POST)
    public CommonResult cancelTimeOutOrder(){
        portalOrderService.cancelTimeOutOrder();
        return CommonResult.success(null);
    }

    @ApiOperation("取消单个超时订单")
    @RequestMapping(value = "/cancelOrder",method = RequestMethod.POST)
    public CommonResult cancelOrder(Long orderId){
        portalOrderService.sendDelayMessageCancelOrder(orderId);
        return CommonResult.success(null);
    }

    @ApiOperation("创建支付 URL")
    @RequestMapping(value = "/createPay",method = RequestMethod.POST)
    public CommonResult pay(PaymentDto dto){
        try {
            String url = portalOrderService.createPay(dto);
            return CommonResult.success(url);
        }catch(RuntimeException e){
            log.warn("Failed to create pay url.", e);
            return CommonResult.failed(e.getMessage());
        }
    }

    /// 我的订单列表
    @ApiOperation("获取我的订单列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult list(OmsOrderQueryParam queryParam,
                             @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        queryParam.setMemberId(""+MemberSecurityUtil.getCurrentMember().getId());
        List<OmsOrder> orderList = orderService.list(queryParam, pageSize, pageNum);
        return CommonResult.success(orderList);
    }

    /// 单个订单详情
    @ApiOperation("获取订单详情:订单信息、商品信息、操作记录")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public CommonResult detail(@RequestParam(value = "id", required = false, defaultValue = "0") Long id) {
        // TODO 复制后台API，需要去掉后台操作记录
        OmsOrderDetail orderDetailResult = orderService.detail(id);
        return CommonResult.success(orderDetailResult);
    }


   private static Logger log = LoggerFactory.getLogger(OmsPortalOrderController.class);
}
