package org.openmall.mall.cms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.openmall.mall.cms.model.CmsSubjectComment;
import org.openmall.mall.cms.service.CmsSubjectCommentService;
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
 * 专题评论表
 * </p>
 */
@Slf4j
@RestController
@Api(tags = "CmsSubjectCommentController", description = "专题评论表管理")
@RequestMapping("/cms/subjectComment")
public class CmsSubjectCommentController {
    @Resource
    private CmsSubjectCommentService subjectCommentService;

    @SysLog(MODULE = "cms", REMARK = "根据条件查询所有专题评论表列表")
    @ApiOperation("根据条件查询所有专题评论表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('cms:subjectComment:read')")
    public Object getCmsSubjectCommentByPage(CmsSubjectComment entity,
                                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                             @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return CommonResult.success(subjectCommentService.page(new PageInfo<CmsSubjectComment>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有专题评论表列表：%s", e.getMessage(), e);
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "保存专题评论表")
    @ApiOperation("保存专题评论表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('cms:subjectComment:create')")
    public Object saveCmsSubjectComment(@RequestBody CmsSubjectComment entity) {
        try {
            if (subjectCommentService.save(entity)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("保存专题评论表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "更新专题评论表")
    @ApiOperation("更新专题评论表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('cms:subjectComment:update')")
    public Object updateCmsSubjectComment(@RequestBody CmsSubjectComment entity) {
        try {
            if (subjectCommentService.updateById(entity)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("更新专题评论表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "删除专题评论表")
    @ApiOperation("删除专题评论表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('cms:subjectComment:delete')")
    public Object deleteCmsSubjectComment(@ApiParam("专题评论表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return CommonResult.paramFailed("专题评论表id");
            }
            if (subjectCommentService.removeById(id)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("删除专题评论表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "给专题评论表分配专题评论表")
    @ApiOperation("查询专题评论表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('cms:subjectComment:read')")
    public Object getCmsSubjectCommentById(@ApiParam("专题评论表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return CommonResult.paramFailed("专题评论表id");
            }
            CmsSubjectComment coupon = subjectCommentService.getById(id);
            return CommonResult.success(coupon);
        } catch (Exception e) {
            log.error("查询专题评论表明细：%s", e.getMessage(), e);
            return CommonResult.failed();
        }

    }

    @ApiOperation(value = "批量删除专题评论表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.GET)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除专题评论表")
    @PreAuthorize("hasAuthority('cms:subjectComment:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = subjectCommentService.removeByIds(ids);
        if (count) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

}
