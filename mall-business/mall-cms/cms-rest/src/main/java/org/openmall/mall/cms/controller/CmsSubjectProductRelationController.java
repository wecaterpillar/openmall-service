package org.openmall.mall.cms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.openmall.mall.cms.model.CmsSubjectProductRelation;
import org.openmall.mall.cms.service.CmsSubjectProductRelationService;
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
 * 专题商品关系表
 * </p>
 */
@Slf4j
@RestController
@Api(tags = "CmsSubjectProductRelationController", description = "专题商品关系表管理")
@RequestMapping("/cms/CmsSubjectProductRelation")
public class CmsSubjectProductRelationController {
    @Resource
    private CmsSubjectProductRelationService subjectProductRelationService;

    @SysLog(MODULE = "cms", REMARK = "根据条件查询所有专题商品关系表列表")
    @ApiOperation("根据条件查询所有专题商品关系表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('cms:subjectProductRelation:read')")
    public Object getCmsSubjectProductRelationByPage(CmsSubjectProductRelation entity,
                                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                     @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return CommonResult.success(subjectProductRelationService.page(new PageInfo<CmsSubjectProductRelation>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有专题商品关系表列表：%s", e.getMessage(), e);
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "保存专题商品关系表")
    @ApiOperation("保存专题商品关系表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('cms:subjectProductRelation:create')")
    public Object saveCmsSubjectProductRelation(@RequestBody CmsSubjectProductRelation entity) {
        try {
            if (subjectProductRelationService.save(entity)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("保存专题商品关系表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "更新专题商品关系表")
    @ApiOperation("更新专题商品关系表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('cms:subjectProductRelation:update')")
    public Object updateCmsSubjectProductRelation(@RequestBody CmsSubjectProductRelation entity) {
        try {
            if (subjectProductRelationService.updateById(entity)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("更新专题商品关系表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "删除专题商品关系表")
    @ApiOperation("删除专题商品关系表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('cms:subjectProductRelation:delete')")
    public Object deleteCmsSubjectProductRelation(@ApiParam("专题商品关系表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return CommonResult.paramFailed("专题商品关系表id");
            }
            if (subjectProductRelationService.removeById(id)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("删除专题商品关系表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "给专题商品关系表分配专题商品关系表")
    @ApiOperation("查询专题商品关系表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('cms:subjectProductRelation:read')")
    public Object getCmsSubjectProductRelationById(@ApiParam("专题商品关系表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return CommonResult.paramFailed("专题商品关系表id");
            }
            CmsSubjectProductRelation coupon = subjectProductRelationService.getById(id);
            return CommonResult.success(coupon);
        } catch (Exception e) {
            log.error("查询专题商品关系表明细：%s", e.getMessage(), e);
            return CommonResult.failed();
        }

    }

    @ApiOperation(value = "批量删除专题商品关系表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.GET)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除专题商品关系表")
    @PreAuthorize("hasAuthority('cms:subjectProductRelation:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = subjectProductRelationService.removeByIds(ids);
        if (count) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

}
