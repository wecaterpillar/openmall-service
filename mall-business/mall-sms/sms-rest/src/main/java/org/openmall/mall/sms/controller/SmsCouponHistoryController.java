package org.openmall.mall.sms.controller;

import org.openmall.mall.common.api.CommonPage;
import org.openmall.mall.common.api.CommonResult;
import org.openmall.mall.common.api.PageInfo;
import org.openmall.mall.sms.model.SmsCouponHistory;
import org.openmall.mall.sms.service.SmsCouponHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 优惠券领取记录管理Controller
 */
@Controller
@Api(tags = "SmsCouponHistoryController", description = "优惠券领取记录管理")
@RequestMapping("/sms/couponHistory")
public class SmsCouponHistoryController {
    @Autowired
    private SmsCouponHistoryService historyService;

    @ApiOperation("根据优惠券id，使用状态，订单编号分页获取领取记录")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<SmsCouponHistory>> list(@RequestParam(value = "couponId", required = false) Long couponId,
                                                           @RequestParam(value = "useStatus", required = false) Integer useStatus,
                                                           @RequestParam(value = "orderSn", required = false) String orderSn,
                                                           @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                           @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SmsCouponHistory> historyList = historyService.list(couponId, useStatus, orderSn, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(new PageInfo(pageNum, pageSize), historyList));
    }
}
