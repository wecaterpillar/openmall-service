package org.openmall.mall.cms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.openmall.mall.cms.model.CmsTopicCategory;
import org.openmall.mall.cms.service.CmsTopicCategoryService;
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
 * 话题分类表
 * </p>

 */
@Slf4j
@RestController
@Api(tags = "CmsTopicCategoryController", description = "话题分类表管理")
@RequestMapping("/cms/topicCategory")
public class CmsTopicCategoryController {
    @Resource
    private CmsTopicCategoryService topicCategoryService;

    @SysLog(MODULE = "cms", REMARK = "根据条件查询所有话题分类表列表")
    @ApiOperation("根据条件查询所有话题分类表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('cms:topicCategory:read')")
    public Object getCmsTopicCategoryByPage(CmsTopicCategory entity,
                                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize
    ) {
        try {
            return CommonResult.success(topicCategoryService.page(new PageInfo<CmsTopicCategory>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有话题分类表列表：%s", e.getMessage(), e);
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "保存话题分类表")
    @ApiOperation("保存话题分类表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('cms:topicCategory:create')")
    public Object saveCmsTopicCategory(@RequestBody CmsTopicCategory entity) {
        try {
            if (topicCategoryService.save(entity)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("保存话题分类表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "更新话题分类表")
    @ApiOperation("更新话题分类表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('cms:topicCategory:update')")
    public Object updateCmsTopicCategory(@RequestBody CmsTopicCategory entity) {
        try {
            if (topicCategoryService.updateById(entity)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("更新话题分类表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "删除话题分类表")
    @ApiOperation("删除话题分类表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('cms:topicCategory:delete')")
    public Object deleteCmsTopicCategory(@ApiParam("话题分类表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return CommonResult.paramFailed("话题分类表id");
            }
            if (topicCategoryService.removeById(id)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("删除话题分类表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "给话题分类表分配话题分类表")
    @ApiOperation("查询话题分类表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('cms:topicCategory:read')")
    public Object getCmsTopicCategoryById(@ApiParam("话题分类表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return CommonResult.paramFailed("话题分类表id");
            }
            CmsTopicCategory coupon = topicCategoryService.getById(id);
            return CommonResult.success(coupon);
        } catch (Exception e) {
            log.error("查询话题分类表明细：%s", e.getMessage(), e);
            return CommonResult.failed();
        }

    }

    @ApiOperation(value = "批量删除话题分类表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.GET)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除话题分类表")
    @PreAuthorize("hasAuthority('cms:topicCategory:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = topicCategoryService.removeByIds(ids);
        if (count) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

}
