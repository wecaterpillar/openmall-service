package org.openmall.mall.cms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.openmall.mall.cms.model.CmsHelpCategory;
import org.openmall.mall.cms.service.CmsHelpCategoryService;
import org.openmall.mall.common.annotation.SysLog;
import org.openmall.mall.common.api.CommonResult;
import org.openmall.mall.common.api.PageInfo;
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
 * 帮助分类表
 * </p>
 *
 */
@Slf4j
@RestController
@Api(tags = "CmsHelpCategoryController", description = "帮助分类表管理")
@RequestMapping("/cms/helpCategory")
public class CmsHelpCategoryController {
    @Resource
    private CmsHelpCategoryService helpCategoryService;

    @SysLog(MODULE = "cms", REMARK = "根据条件查询所有帮助分类表列表")
    @ApiOperation("根据条件查询所有帮助分类表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('cms:helpCategory:read')")
    public Object getCmsHelpCategoryByPage(CmsHelpCategory entity,
                                           @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                           @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize
    ) {
        try {
            return CommonResult.success(helpCategoryService.page(new PageInfo<CmsHelpCategory>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有帮助分类表列表：%s", e.getMessage(), e);
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "保存帮助分类表")
    @ApiOperation("保存帮助分类表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('cms:helpCategory:create')")
    public Object saveCmsHelpCategory(@RequestBody CmsHelpCategory entity) {
        try {
            if (helpCategoryService.save(entity)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("保存帮助分类表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "更新帮助分类表")
    @ApiOperation("更新帮助分类表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('cms:helpCategory:update')")
    public Object updateCmsHelpCategory(@RequestBody CmsHelpCategory entity) {
        try {
            if (helpCategoryService.updateById(entity)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("更新帮助分类表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "删除帮助分类表")
    @ApiOperation("删除帮助分类表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('cms:helpCategory:delete')")
    public Object deleteCmsHelpCategory(@ApiParam("帮助分类表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return CommonResult.paramFailed("帮助分类表id");
            }
            if (helpCategoryService.removeById(id)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("删除帮助分类表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "给帮助分类表分配帮助分类表")
    @ApiOperation("查询帮助分类表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('cms:helpCategory:read')")
    public Object getCmsHelpCategoryById(@ApiParam("帮助分类表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return CommonResult.paramFailed("帮助分类表id");
            }
            CmsHelpCategory coupon = helpCategoryService.getById(id);
            return CommonResult.success(coupon);
        } catch (Exception e) {
            log.error("查询帮助分类表明细：%s", e.getMessage(), e);
            return CommonResult.failed();
        }

    }

    @ApiOperation(value = "批量删除帮助分类表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.GET)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除帮助分类表")
    @PreAuthorize("hasAuthority('cms:helpCategory:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = helpCategoryService.removeByIds(ids);
        if (count) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

}
