package org.openmall.mall.ums.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.openmall.mall.common.annotation.SysLog;
import org.openmall.mall.common.api.CommonResult;
import org.openmall.mall.ums.model.UmsMember;
import org.openmall.mall.ums.service.UmsMemberService2;
import org.openmall.mall.validator.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员管理Controller
 */
@Slf4j
@RestController
@Api(tags = "UmsMemberController", description = "会员管理")
@RequestMapping("/ums/member")
public class UmsMemberController2 {


    @Autowired
    private UmsMemberService2 memberService;

    @SysLog(MODULE = "ums", REMARK = "根据条件查询所有会员表列表")
    @ApiOperation("根据条件查询所有会员表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('ums:member:read')")
    public Object getUmsMemberByPage(UmsMember entity,
                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize
    ) {
        try {
            return CommonResult.success(memberService.page(new Page<UmsMember>(pageNum, pageSize), new QueryWrapper<>(entity).orderByDesc("create_time")));
        } catch (Exception e) {
            log.error("根据条件查询所有会员表列表：%s", e.getMessage(), e);
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "ums", REMARK = "保存会员表")
    @ApiOperation("保存会员表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('ums:member:create')")
    public Object saveUmsMember(@RequestBody UmsMember entity) {
        try {
            if (memberService.save(entity)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("保存会员表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "ums", REMARK = "更新会员表")
    @ApiOperation("更新会员表")
    @PostMapping(value = "/update")
    //  @PreAuthorize("hasAuthority('ums:member:update')")
    @Transactional
    public Object updateUmsMember(@RequestBody UmsMember entity) {
        try {
            if (memberService.updateById(entity)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("更新会员表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }


    @SysLog(MODULE = "ums", REMARK = "删除会员表")
    @ApiOperation("删除会员表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('ums:member:delete')")
    public Object deleteUmsMember(@ApiParam("会员表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return CommonResult.paramFailed("会员表id");
            }
            if (memberService.removeById(id)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("删除会员表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "ums", REMARK = "给会员表分配会员表")
    @ApiOperation("查询会员表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ums:member:read')")
    public Object getUmsMemberById(@ApiParam("会员表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return CommonResult.paramFailed("会员表id");
            }
            UmsMember coupon = memberService.getById(id);
            return CommonResult.success(coupon);
        } catch (Exception e) {
            log.error("查询会员表明细：%s", e.getMessage(), e);
            return CommonResult.failed();
        }

    }

    @ApiOperation(value = "批量删除会员表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.GET)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除会员表")
    @PreAuthorize("hasAuthority('ums:member:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = memberService.removeByIds(ids);
        if (count) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("更新会员的订单信息")
    @PostMapping(value = "/updateMemberOrderInfo")
    public Object updateMemberOrderInfo() {
        try {
            memberService.updataMemberOrderInfo();
            return CommonResult.success();
        } catch (Exception e) {
            log.error("更新会员的订单信息：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
    }

}
