package org.openmall.mall.cms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.openmall.mall.cms.model.CmsTopicComment;
import org.openmall.mall.cms.service.CmsTopicCommentService;
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
 * 专题评论表
 * </p>
 *
 */
@Slf4j
@RestController
@Api(tags = "CmsTopicCommentController", description = "专题评论表管理")
@RequestMapping("/cms/topicComment")
public class CmsTopicCommentController {
    @Resource
    private CmsTopicCommentService topicCommentService;

    @SysLog(MODULE = "cms", REMARK = "根据条件查询所有专题评论表列表")
    @ApiOperation("根据条件查询所有专题评论表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('cms:topicComment:read')")
    public Object getCmsTopicCommentByPage(CmsTopicComment entity,
                                           @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                           @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize
    ) {
        try {
            return CommonResult.success(topicCommentService.page(new PageInfo<CmsTopicComment>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有专题评论表列表：%s", e.getMessage(), e);
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "保存专题评论表")
    @ApiOperation("保存专题评论表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('cms:topicComment:create')")
    public Object saveCmsTopicComment(@RequestBody CmsTopicComment entity) {
        try {
            if (topicCommentService.save(entity)) {
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
    @PreAuthorize("hasAuthority('cms:topicComment:update')")
    public Object updateCmsTopicComment(@RequestBody CmsTopicComment entity) {
        try {
            if (topicCommentService.updateById(entity)) {
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
    @PreAuthorize("hasAuthority('cms:topicComment:delete')")
    public Object deleteCmsTopicComment(@ApiParam("专题评论表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return CommonResult.paramFailed("专题评论表id");
            }
            if (topicCommentService.removeById(id)) {
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
    @PreAuthorize("hasAuthority('cms:topicComment:read')")
    public Object getCmsTopicCommentById(@ApiParam("专题评论表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return CommonResult.paramFailed("专题评论表id");
            }
            CmsTopicComment coupon = topicCommentService.getById(id);
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
    @PreAuthorize("hasAuthority('cms:topicComment:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = topicCommentService.removeByIds(ids);
        if (count) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

}
