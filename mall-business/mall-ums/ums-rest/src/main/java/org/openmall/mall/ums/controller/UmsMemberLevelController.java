package org.openmall.mall.ums.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.openmall.mall.common.api.CommonResult;
import org.openmall.mall.common.service.IBaseService;
import org.openmall.mall.sms.dto.SmsCouponParam;
import org.openmall.mall.ums.model.UmsMemberLevel;
import org.openmall.mall.ums.service.UmsMemberLevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员等级管理Controller
 */
@Slf4j
@Controller
@Api(tags = "UmsMemberLevelController", description = "会员等级管理")
@RequestMapping("/ums/memberLevel")
public class UmsMemberLevelController {

    @Autowired
    private UmsMemberLevelService memberLevelService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation("查询所有会员等级")
    @ResponseBody
    public CommonResult<List<UmsMemberLevel>> list(@RequestParam("defaultStatus") Integer defaultStatus) {
        List<UmsMemberLevel> memberLevelList = memberLevelService.list(defaultStatus);
        return CommonResult.success(memberLevelList);
    }

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ApiOperation("查询所有会员等级")
    @ResponseBody
    public CommonResult<List<UmsMemberLevel>> listAll() {
        List<UmsMemberLevel> memberLevelList = memberLevelService.list();
        return CommonResult.success(memberLevelList);
    }

    @ApiOperation("获取单个会员等级的详细信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UmsMemberLevel> getItem(@PathVariable Long id) {

        UmsMemberLevel level = memberLevelService.getById(id);
        return CommonResult.success(level);
    }

    @ApiOperation(value = "更新会员等级")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    //@PreAuthorize("hasAuthority('ums:memberLevel:update')")
    public CommonResult update(@PathVariable("id") Long id,
                               @Validated @RequestBody UmsMemberLevel umsMemberLevel,
                               BindingResult result) {
        CommonResult commonResult;
        boolean execResult = memberLevelService.saveOrUpdate(umsMemberLevel);
        if (execResult) {
            commonResult = CommonResult.success(1);
        } else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }
}
