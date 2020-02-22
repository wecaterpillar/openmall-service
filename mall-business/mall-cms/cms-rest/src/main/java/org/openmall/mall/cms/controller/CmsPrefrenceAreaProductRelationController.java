package org.openmall.mall.cms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.openmall.mall.cms.model.CmsPrefrenceAreaProductRelation;
import org.openmall.mall.cms.service.CmsPrefrenceAreaProductRelationService;
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
 * 优选专区和产品关系表
 * </p>
 *
 */
@Slf4j
@RestController
@Api(tags = "CmsPrefrenceAreaProductRelationController", description = "优选专区和产品关系表管理")
@RequestMapping("/cms/prefrenceAreaProductRelation")
public class CmsPrefrenceAreaProductRelationController {
    @Resource
    private CmsPrefrenceAreaProductRelationService prefrenceAreaProductRelationService;

    @SysLog(MODULE = "cms", REMARK = "根据条件查询所有优选专区和产品关系表列表")
    @ApiOperation("根据条件查询所有优选专区和产品关系表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('cms:prefrenceAreaProductRelation:read')")
    public Object getCmsPrefrenceAreaProductRelationByPage(CmsPrefrenceAreaProductRelation entity,
                                                           @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                           @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize
    ) {
        try {
            return CommonResult.success(prefrenceAreaProductRelationService.page(new Page<CmsPrefrenceAreaProductRelation>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有优选专区和产品关系表列表：%s", e.getMessage(), e);
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "保存优选专区和产品关系表")
    @ApiOperation("保存优选专区和产品关系表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('cms:prefrenceAreaProductRelation:create')")
    public Object saveCmsPrefrenceAreaProductRelation(@RequestBody CmsPrefrenceAreaProductRelation entity) {
        try {
            if (prefrenceAreaProductRelationService.save(entity)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("保存优选专区和产品关系表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "更新优选专区和产品关系表")
    @ApiOperation("更新优选专区和产品关系表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('cms:prefrenceAreaProductRelation:update')")
    public Object updateCmsPrefrenceAreaProductRelation(@RequestBody CmsPrefrenceAreaProductRelation entity) {
        try {
            if (prefrenceAreaProductRelationService.updateById(entity)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("更新优选专区和产品关系表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "删除优选专区和产品关系表")
    @ApiOperation("删除优选专区和产品关系表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('cms:prefrenceAreaProductRelation:delete')")
    public Object deleteCmsPrefrenceAreaProductRelation(@ApiParam("优选专区和产品关系表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return CommonResult.paramFailed("优选专区和产品关系表id");
            }
            if (prefrenceAreaProductRelationService.removeById(id)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("删除优选专区和产品关系表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "给优选专区和产品关系表分配优选专区和产品关系表")
    @ApiOperation("查询优选专区和产品关系表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('cms:prefrenceAreaProductRelation:read')")
    public Object getCmsPrefrenceAreaProductRelationById(@ApiParam("优选专区和产品关系表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return CommonResult.paramFailed("优选专区和产品关系表id");
            }
            CmsPrefrenceAreaProductRelation coupon = prefrenceAreaProductRelationService.getById(id);
            return CommonResult.success(coupon);
        } catch (Exception e) {
            log.error("查询优选专区和产品关系表明细：%s", e.getMessage(), e);
            return CommonResult.failed();
        }

    }

    @ApiOperation(value = "批量删除优选专区和产品关系表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.GET)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除优选专区和产品关系表")
    @PreAuthorize("hasAuthority('cms:prefrenceAreaProductRelation:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = prefrenceAreaProductRelationService.removeByIds(ids);
        if (count) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

}
