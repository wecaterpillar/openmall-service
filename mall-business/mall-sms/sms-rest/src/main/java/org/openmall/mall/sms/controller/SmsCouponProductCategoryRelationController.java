package org.openmall.mall.sms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.openmall.mall.common.annotation.SysLog;
import org.openmall.mall.common.api.CommonResult;
import org.openmall.mall.common.api.PageInfo;
import org.openmall.mall.sms.model.SmsCouponProductCategoryRelation;
import org.openmall.mall.sms.service.SmsCouponProductCategoryRelationService;
import org.openmall.mall.validator.ValidatorUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 优惠券和产品分类关系表
 * </p>
 *
 */
@Slf4j
@RestController
@Api(tags = "SmsCouponProductCategoryRelationController", description = "优惠券和产品分类关系表管理")
@RequestMapping("/sms/couponProductCategoryRelation")
public class SmsCouponProductCategoryRelationController {
    @Resource
    private SmsCouponProductCategoryRelationService couponProductCategoryRelationService;

    @SysLog(MODULE = "sms", REMARK = "根据条件查询所有优惠券和产品分类关系表列表")
    @ApiOperation("根据条件查询所有优惠券和产品分类关系表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('sms:couponProductCategoryRelation:read')")
    public Object getSmsCouponProductCategoryRelationByPage(SmsCouponProductCategoryRelation entity,
                                                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize
    ) {
        try {
            return CommonResult.success(couponProductCategoryRelationService.page(new PageInfo<SmsCouponProductCategoryRelation>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有优惠券和产品分类关系表列表：%s", e.getMessage(), e);
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "sms", REMARK = "保存优惠券和产品分类关系表")
    @ApiOperation("保存优惠券和产品分类关系表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('sms:couponProductCategoryRelation:create')")
    public Object saveSmsCouponProductCategoryRelation(@RequestBody SmsCouponProductCategoryRelation entity) {
        try {
            if (couponProductCategoryRelationService.save(entity)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("保存优惠券和产品分类关系表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "sms", REMARK = "更新优惠券和产品分类关系表")
    @ApiOperation("更新优惠券和产品分类关系表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('sms:couponProductCategoryRelation:update')")
    public Object updateSmsCouponProductCategoryRelation(@RequestBody SmsCouponProductCategoryRelation entity) {
        try {
            if (couponProductCategoryRelationService.updateById(entity)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("更新优惠券和产品分类关系表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "sms", REMARK = "删除优惠券和产品分类关系表")
    @ApiOperation("删除优惠券和产品分类关系表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('sms:couponProductCategoryRelation:delete')")
    public Object deleteSmsCouponProductCategoryRelation(@ApiParam("优惠券和产品分类关系表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return CommonResult.paramFailed("优惠券和产品分类关系表id");
            }
            if (couponProductCategoryRelationService.removeById(id)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("删除优惠券和产品分类关系表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "sms", REMARK = "给优惠券和产品分类关系表分配优惠券和产品分类关系表")
    @ApiOperation("查询优惠券和产品分类关系表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('sms:couponProductCategoryRelation:read')")
    public Object getSmsCouponProductCategoryRelationById(@ApiParam("优惠券和产品分类关系表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return CommonResult.paramFailed("优惠券和产品分类关系表id");
            }
            SmsCouponProductCategoryRelation coupon = couponProductCategoryRelationService.getById(id);
            return CommonResult.success(coupon);
        } catch (Exception e) {
            log.error("查询优惠券和产品分类关系表明细：%s", e.getMessage(), e);
            return CommonResult.failed();
        }

    }

    @ApiOperation(value = "批量删除优惠券和产品分类关系表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.GET)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除优惠券和产品分类关系表")
    @PreAuthorize("hasAuthority('sms:couponProductCategoryRelation:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = couponProductCategoryRelationService.removeByIds(ids);
        if (count) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

}
