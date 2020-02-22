package org.openmall.mall.sms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.openmall.mall.common.annotation.SysLog;
import org.openmall.mall.common.api.CommonResult;
import org.openmall.mall.sms.model.SmsFlashPromotionLog;
import org.openmall.mall.sms.service.SmsFlashPromotionLogService;
import org.openmall.mall.validator.ValidatorUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 限时购通知记录
 * </p>
 */
@Slf4j
@RestController
@Api(tags = "SmsFlashPromotionLogController", description = "限时购通知记录管理")
@RequestMapping("/sms/SmsFlashPromotionLog")
public class SmsFlashPromotionLogController {
    @Resource
    private SmsFlashPromotionLogService flashPromotionLogService;

    @SysLog(MODULE = "sms", REMARK = "根据条件查询所有限时购通知记录列表")
    @ApiOperation("根据条件查询所有限时购通知记录列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('sms:flashPromotionLog:read')")
    public Object getSmsFlashPromotionLogByPage(SmsFlashPromotionLog entity,
                                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize
    ) {
        try {
            return CommonResult.success(flashPromotionLogService.page(new Page<SmsFlashPromotionLog>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有限时购通知记录列表：%s", e.getMessage(), e);
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "sms", REMARK = "保存限时购通知记录")
    @ApiOperation("保存限时购通知记录")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('sms:flashPromotionLog:create')")
    public Object saveSmsFlashPromotionLog(@RequestBody SmsFlashPromotionLog entity) {
        try {
            if (flashPromotionLogService.save(entity)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("保存限时购通知记录：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "sms", REMARK = "更新限时购通知记录")
    @ApiOperation("更新限时购通知记录")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('sms:flashPromotionLog:update')")
    public Object updateSmsFlashPromotionLog(@RequestBody SmsFlashPromotionLog entity) {
        try {
            if (flashPromotionLogService.updateById(entity)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("更新限时购通知记录：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "sms", REMARK = "删除限时购通知记录")
    @ApiOperation("删除限时购通知记录")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('sms:flashPromotionLog:delete')")
    public Object deleteSmsFlashPromotionLog(@ApiParam("限时购通知记录id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return CommonResult.paramFailed("限时购通知记录id");
            }
            if (flashPromotionLogService.removeById(id)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("删除限时购通知记录：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "sms", REMARK = "给限时购通知记录分配限时购通知记录")
    @ApiOperation("查询限时购通知记录明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('sms:flashPromotionLog:read')")
    public Object getSmsFlashPromotionLogById(@ApiParam("限时购通知记录id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return CommonResult.paramFailed("限时购通知记录id");
            }
            SmsFlashPromotionLog coupon = flashPromotionLogService.getById(id);
            return CommonResult.success(coupon);
        } catch (Exception e) {
            log.error("查询限时购通知记录明细：%s", e.getMessage(), e);
            return CommonResult.failed();
        }

    }

    @ApiOperation(value = "批量删除限时购通知记录")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.GET)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除限时购通知记录")
    @PreAuthorize("hasAuthority('sms:flashPromotionLog:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = flashPromotionLogService.removeByIds(ids);
        if (count) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

}
