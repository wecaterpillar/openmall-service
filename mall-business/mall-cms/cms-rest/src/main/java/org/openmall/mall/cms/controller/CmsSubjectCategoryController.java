package org.openmall.mall.cms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.openmall.mall.cms.model.CmsSubjectCategory;
import org.openmall.mall.cms.service.CmsSubjectCategoryService;
import org.openmall.mall.common.annotation.SysLog;
import org.openmall.mall.common.api.CommonResult;
import org.openmall.mall.common.api.PageInfo;
import org.openmall.mall.validator.ValidatorUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 专题分类表
 * </p>
 */
@Slf4j
@RestController
@Api(tags = "CmsSubjectCategoryController", description = "专题分类表管理")
@RequestMapping("/cms/subjectCategory")
public class CmsSubjectCategoryController {
    @Resource
    private CmsSubjectCategoryService subjectCategoryService;

    @SysLog(MODULE = "cms", REMARK = "根据条件查询所有专题分类表列表")
    @ApiOperation("根据条件查询所有专题分类表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('cms:subjectCategory:read')")
    public Object getCmsSubjectCategoryByPage(CmsSubjectCategory entity,
                                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                              @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return CommonResult.success(subjectCategoryService.page(new PageInfo<CmsSubjectCategory>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有专题分类表列表：%s", e.getMessage(), e);
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "保存专题分类表")
    @ApiOperation("保存专题分类表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('cms:subjectCategory:create')")
    public Object saveCmsSubjectCategory(@RequestBody CmsSubjectCategory entity) {
        try {
            if (subjectCategoryService.save(entity)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("保存专题分类表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "更新专题分类表")
    @ApiOperation("更新专题分类表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('cms:subjectCategory:update')")
    public Object updateCmsSubjectCategory(@RequestBody CmsSubjectCategory entity) {
        try {
            if (subjectCategoryService.updateById(entity)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("更新专题分类表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "删除专题分类表")
    @ApiOperation("删除专题分类表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('cms:subjectCategory:delete')")
    public Object deleteCmsSubjectCategory(@ApiParam("专题分类表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return CommonResult.paramFailed("专题分类表id");
            }
            if (subjectCategoryService.removeById(id)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("删除专题分类表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "给专题分类表分配专题分类表")
    @ApiOperation("查询专题分类表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('cms:subjectCategory:read')")
    public Object getCmsSubjectCategoryById(@ApiParam("专题分类表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return CommonResult.paramFailed("专题分类表id");
            }
            CmsSubjectCategory coupon = subjectCategoryService.getById(id);
            return CommonResult.success(coupon);
        } catch (Exception e) {
            log.error("查询专题分类表明细：%s", e.getMessage(), e);
            return CommonResult.failed();
        }

    }

    @ApiOperation(value = "批量删除专题分类表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.GET)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除专题分类表")
    @PreAuthorize("hasAuthority('cms:subjectCategory:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = subjectCategoryService.removeByIds(ids);
        if (count) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

}
