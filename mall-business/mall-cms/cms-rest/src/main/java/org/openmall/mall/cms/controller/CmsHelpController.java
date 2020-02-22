package org.openmall.mall.cms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.openmall.mall.cms.model.CmsHelp;
import org.openmall.mall.cms.service.CmsHelpService;
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
 * 帮助表
 * </p>
 */
@Slf4j
@RestController
@Api(tags = "CmsHelpController", description = "帮助表管理")
@RequestMapping("/cms/help")
public class CmsHelpController {
    @Resource
    private CmsHelpService helpService;

    @SysLog(MODULE = "cms", REMARK = "根据条件查询所有帮助表列表")
    @ApiOperation("根据条件查询所有帮助表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('cms:help:read')")
    public Object getCmsHelpByPage(CmsHelp entity,
                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize
    ) {
        try {
            return CommonResult.success(helpService.page(new Page<CmsHelp>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有帮助表列表：%s", e.getMessage(), e);
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "保存帮助表")
    @ApiOperation("保存帮助表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('cms:help:create')")
    public Object saveCmsHelp(@RequestBody CmsHelp entity) {
        try {
            if (helpService.saves(entity)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("保存帮助表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "更新帮助表")
    @ApiOperation("更新帮助表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('cms:help:update')")
    public Object updateCmsHelp(@RequestBody CmsHelp entity) {
        try {
            if (helpService.updateById(entity)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("更新帮助表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "删除帮助表")
    @ApiOperation("删除帮助表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('cms:help:delete')")
    public Object deleteCmsHelp(@ApiParam("帮助表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return CommonResult.paramFailed("帮助表id");
            }
            if (helpService.removeById(id)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("删除帮助表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "给帮助表分配帮助表")
    @ApiOperation("查询帮助表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('cms:help:read')")
    public Object getCmsHelpById(@ApiParam("帮助表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return CommonResult.paramFailed("帮助表id");
            }
            CmsHelp coupon = helpService.getById(id);
            return CommonResult.success(coupon);
        } catch (Exception e) {
            log.error("查询帮助表明细：%s", e.getMessage(), e);
            return CommonResult.failed();
        }

    }

    @ApiOperation(value = "批量删除帮助表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.GET)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除帮助表")
    @PreAuthorize("hasAuthority('cms:help:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = helpService.removeByIds(ids);
        if (count) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

}
