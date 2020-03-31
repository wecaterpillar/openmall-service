package org.openmall.mall.portal.ums.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.openmall.mall.common.api.CommonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * mail subsrible
 */
@RestController
@Api(tags = "MailSubscribeController", description = "邮件订阅管理")
@RequestMapping("/subscribe")
public class MailSubscribeController {

    @ApiOperation("添加邮件订阅")
    @RequestMapping(value = "/sub", method = RequestMethod.POST)
    public CommonResult sub(@RequestParam(value = "mailAddress", required = true) String mailAddress,
                            @RequestParam(value = "memberId", required = false) Long memberId) {
        //TODO 增加或更新到邮件订阅列表
        return CommonResult.success(1);
    }

    @ApiOperation("取消邮件订阅")
    @RequestMapping(value = "/unsub", method = RequestMethod.POST)
    public CommonResult unsub(@RequestParam(value = "mailAddress", required = false) String mailAddress,
                              @RequestParam(value = "memberId", required = false) Long memberId) {
        // TODO 更新邮件订阅状态（逻辑删除）
        return CommonResult.success(1);
    }
}
