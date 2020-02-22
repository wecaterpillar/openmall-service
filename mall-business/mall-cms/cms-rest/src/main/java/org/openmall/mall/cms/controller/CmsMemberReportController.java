package org.openmall.mall.cms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.openmall.mall.cms.model.CmsMemberReport;
import org.openmall.mall.cms.service.CmsMemberReportService;
import org.openmall.mall.common.annotation.SysLog;
import org.openmall.mall.common.api.CommonResult;
import org.openmall.mall.validator.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户举报表
 * </p>
 *
 */
@Slf4j
@RestController
@Api(tags = "CmsMemberReportController", description = "用户举报表管理")
@RequestMapping("/cms/memberReport")
public class CmsMemberReportController {
    @Resource
    private CmsMemberReportService memberReportService;

    @SysLog(MODULE = "cms", REMARK = "根据条件查询所有用户举报表列表")
    @ApiOperation("根据条件查询所有用户举报表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('cms:memberReport:read')")
    public Object getCmsMemberReportByPage(CmsMemberReport entity,
                                           @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                           @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize
    ) {
        try {
            return CommonResult.success(memberReportService.page(new Page<CmsMemberReport>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有用户举报表列表：%s", e.getMessage(), e);
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "保存用户举报表")
    @ApiOperation("保存用户举报表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('cms:memberReport:create')")
    public Object saveCmsMemberReport(@RequestBody CmsMemberReport entity) {
        try {
            if (memberReportService.save(entity)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("保存用户举报表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "更新用户举报表")
    @ApiOperation("更新用户举报表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('cms:memberReport:update')")
    public Object updateCmsMemberReport(@RequestBody CmsMemberReport entity) {
        try {
            if (memberReportService.updateById(entity)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("更新用户举报表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "删除用户举报表")
    @ApiOperation("删除用户举报表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('cms:memberReport:delete')")
    public Object deleteCmsMemberReport(@ApiParam("用户举报表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return CommonResult.paramFailed("用户举报表id");
            }
            if (memberReportService.removeById(id)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("删除用户举报表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "给用户举报表分配用户举报表")
    @ApiOperation("查询用户举报表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('cms:memberReport:read')")
    public Object getCmsMemberReportById(@ApiParam("用户举报表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return CommonResult.paramFailed("用户举报表id");
            }
            CmsMemberReport coupon = memberReportService.getById(id);
            return CommonResult.success(coupon);
        } catch (Exception e) {
            log.error("查询用户举报表明细：%s", e.getMessage(), e);
            return CommonResult.failed();
        }

    }

    @ApiOperation(value = "批量删除用户举报表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.GET)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除用户举报表")
    @PreAuthorize("hasAuthority('cms:memberReport:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = memberReportService.removeByIds(ids);
        if (count) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

}
