package org.openmall.mall.ums.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.openmall.mall.common.annotation.SysLog;
import org.openmall.mall.common.api.CommonResult;
import org.openmall.mall.ums.model.UmsMemberLevel;
import org.openmall.mall.ums.service.UmsMemberLevelService;
import org.openmall.mall.validator.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员等级管理Controller
 */
@Slf4j
@RestController
@Api(tags = "UmsMemberLevelController", description = "会员等级管理")
@RequestMapping("/ums/memberLevel")
public class UmsMemberLevelController {

    @Autowired
    private UmsMemberLevelService memberLevelService;

    @RequestMapping(value = "/list2", method = RequestMethod.GET)
    @ApiOperation("查询所有会员等级")
    public CommonResult<List<UmsMemberLevel>> list(@RequestParam("defaultStatus") Integer defaultStatus) {
        List<UmsMemberLevel> memberLevelList = memberLevelService.list(defaultStatus);
        return CommonResult.success(memberLevelList);
    }

    @SysLog(MODULE = "ums", REMARK = "根据条件查询所有会员等级表列表")
    @ApiOperation("根据条件查询所有会员等级表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('ums:memberLevel:read')")
    public Object getUmsMemberLevelByPage(UmsMemberLevel entity,
                                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                          @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return CommonResult.success(memberLevelService.page(new Page<UmsMemberLevel>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有会员等级表列表：%s", e.getMessage(), e);
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "ums", REMARK = "保存会员等级表")
    @ApiOperation("保存会员等级表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('ums:memberLevel:create')")
    public Object saveUmsMemberLevel(@RequestBody UmsMemberLevel entity) {
        try {
            if (memberLevelService.save(entity)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("保存会员等级表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "ums", REMARK = "更新会员等级表")
    @ApiOperation("更新会员等级表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('ums:memberLevel:update')")
    public Object updateUmsMemberLevel(@RequestBody UmsMemberLevel entity) {
        try {
            if (memberLevelService.updateById(entity)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("更新会员等级表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "ums", REMARK = "删除会员等级表")
    @ApiOperation("删除会员等级表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('ums:memberLevel:delete')")
    public Object deleteUmsMemberLevel(@ApiParam("会员等级表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return CommonResult.paramFailed("会员等级表id");
            }
            if (memberLevelService.removeById(id)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("删除会员等级表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "ums", REMARK = "给会员等级表分配会员等级表")
    @ApiOperation("查询会员等级表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ums:memberLevel:read')")
    public Object getUmsMemberLevelById(@ApiParam("会员等级表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return CommonResult.paramFailed("会员等级表id");
            }
            UmsMemberLevel coupon = memberLevelService.getById(id);
            return CommonResult.success(coupon);
        } catch (Exception e) {
            log.error("查询会员等级表明细：%s", e.getMessage(), e);
            return CommonResult.failed();
        }

    }

    @ApiOperation(value = "批量删除会员等级表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.GET)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除会员等级表")
    @PreAuthorize("hasAuthority('ums:memberLevel:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = memberLevelService.removeByIds(ids);
        if (count) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

}
