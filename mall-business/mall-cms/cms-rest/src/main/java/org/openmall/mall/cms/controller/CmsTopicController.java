package org.openmall.mall.cms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.openmall.mall.cms.model.CmsTopic;
import org.openmall.mall.cms.service.CmsTopicService;
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
 * 话题表
 * </p>
 *
 */
@Slf4j
@RestController
@Api(tags = "CmsTopicController", description = "话题表管理")
@RequestMapping("/cms/topic")
public class CmsTopicController {
    @Resource
    private CmsTopicService topicService;
//    @Resource
//    private CmsTopicMemberService topicMemberService;

    @SysLog(MODULE = "cms", REMARK = "根据条件查询所有话题表列表")
    @ApiOperation("根据条件查询所有话题表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('cms:topic:read')")
    public Object getCmsTopicByPage(CmsTopic entity,
                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize
    ) {
        try {
            return CommonResult.success(topicService.page(new Page<CmsTopic>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有话题表列表：%s", e.getMessage(), e);
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "保存话题表")
    @ApiOperation("保存话题表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('cms:topic:create')")
    public Object saveCmsTopic(@RequestBody CmsTopic entity) {
        try {
            if (topicService.saves(entity)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("保存话题表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "更新话题表")
    @ApiOperation("更新话题表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('cms:topic:update')")
    public Object updateCmsTopic(@RequestBody CmsTopic entity) {
        try {
            if (topicService.updateById(entity)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("更新话题表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "删除话题表")
    @ApiOperation("删除话题表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('cms:topic:delete')")
    public Object deleteCmsTopic(@ApiParam("话题表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return CommonResult.paramFailed("话题表id");
            }
            if (topicService.removeById(id)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("删除话题表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "给话题表分配话题表")
    @ApiOperation("查询话题表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('cms:topic:read')")
    public Object getCmsTopicById(@ApiParam("话题表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return CommonResult.paramFailed("话题表id");
            }
            CmsTopic coupon = topicService.getById(id);
            return CommonResult.success(coupon);
        } catch (Exception e) {
            log.error("查询话题表明细：%s", e.getMessage(), e);
            return CommonResult.failed();
        }

    }

    @ApiOperation(value = "批量删除话题表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.GET)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除话题表")
    @PreAuthorize("hasAuthority('cms:topic:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = topicService.removeByIds(ids);
        if (count) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改审核状态")
    @RequestMapping(value = "/update/verifyStatus")
    @ResponseBody
    @SysLog(MODULE = "cms", REMARK = "修改审核状态")
    public Object updateVerifyStatus(@RequestParam("ids") Long ids,
                                     @RequestParam("topicId") Long topicId,
                                     @RequestParam("verifyStatus") Integer verifyStatus) {

        int count = topicService.updateVerifyStatus(ids, topicId, verifyStatus);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

//    @ApiOperation("根据活动id获取活动参与人员信息")
//    @RequestMapping(value = "/fetchCmsTopicMember/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    @SysLog(MODULE = "cms", REMARK = "根据活动id获取活动参与人员信息")
//    public Object fetchCmsTopicMember(@PathVariable Long id) {
//        CmsTopicMember member = new CmsTopicMember();
//        member.setTopicId(id);
//        List<CmsTopicMember> list = topicMemberService.list(new QueryWrapper<>(member));
//        return CommonResult.success(list);
//    }
}
