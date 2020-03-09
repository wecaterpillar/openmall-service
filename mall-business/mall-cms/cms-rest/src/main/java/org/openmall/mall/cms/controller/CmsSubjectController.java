package org.openmall.mall.cms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.openmall.mall.common.annotation.SysLog;
import org.openmall.mall.common.api.CommonPage;
import org.openmall.mall.common.api.CommonResult;
import org.openmall.mall.cms.model.CmsSubject;
import org.openmall.mall.cms.service.CmsSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.openmall.mall.common.api.PageInfo;
import org.openmall.mall.validator.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品专题Controller
 */
@Slf4j
@RestController
@Api(tags = "CmsSubjectController", description = "商品专题管理")
@RequestMapping("/cms/subject")
public class CmsSubjectController {

    public static final String sampleSubjectList = "id, category_id, title, pic, product_count, recommend_status, create_time, collect_count, read_count, comment_count, album_pics, description, show_status,  forward_count, category_name";

    @Autowired
    private CmsSubjectService subjectService;

    @ApiOperation("获取全部商品专题")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<CmsSubject>> listAll() {
        List<CmsSubject> subjectList = subjectService.listAll();
        return CommonResult.success(subjectList);
    }

    @ApiOperation(value = "根据专题名称分页获取专题")
    @RequestMapping(value = "/list2", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<CmsSubject>> getList(@RequestParam(value = "keyword", required = false) String keyword,
                                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        List<CmsSubject> subjectList = subjectService.list(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(subjectList));
    }

    @SysLog(MODULE = "cms", REMARK = "根据条件查询所有专题表列表")
    @ApiOperation("根据条件查询所有专题表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('cms:subject:read')")
    public Object getCmsSubjectByPage(CmsSubject entity,
                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return CommonResult.success(subjectService.page(new PageInfo<CmsSubject>(pageNum, pageSize), new QueryWrapper<>(entity).select(sampleSubjectList)));
        } catch (Exception e) {
            log.error("根据条件查询所有专题表列表：%s", e.getMessage(), e);
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "保存专题表")
    @ApiOperation("保存专题表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('cms:subject:create')")
    public Object saveCmsSubject(@RequestBody CmsSubject entity) {
        try {

           /* entity.setSchoolName(member.getSchoolName());
            entity.setSchoolId(member.getSchoolId());
            entity.setAreaName(member.getAreaName());
            entity.setAreaId(member.getAreaId());*/

            if (subjectService.saves(entity)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("保存专题表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "更新专题表")
    @ApiOperation("更新专题表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('cms:subject:update')")
    public Object updateCmsSubject(@RequestBody CmsSubject entity) {
        try {
            if (subjectService.updateById(entity)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("更新专题表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "删除专题表")
    @ApiOperation("删除专题表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('cms:subject:delete')")
    public Object deleteCmsSubject(@ApiParam("专题表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return CommonResult.paramFailed("专题表id");
            }
            if (subjectService.removeById(id)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("删除专题表：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "给专题表分配专题表")
    @ApiOperation("查询专题表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('cms:subject:read')")
    public Object getCmsSubjectById(@ApiParam("专题表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return CommonResult.paramFailed("专题表id");
            }
            CmsSubject coupon = subjectService.getById(id);
            return CommonResult.success(coupon);
        } catch (Exception e) {
            log.error("查询专题表明细：%s", e.getMessage(), e);
            return CommonResult.failed();
        }

    }

    @ApiOperation(value = "批量删除专题表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.GET)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除专题表")
    @PreAuthorize("hasAuthority('cms:subject:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = subjectService.removeByIds(ids);
        if (count) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改推荐状态")
    @RequestMapping(value = "/update/updateRecommendStatus")
    @ResponseBody
    @SysLog(MODULE = "cms", REMARK = "修改推荐状态")
    public Object updateRecommendStatus(@RequestParam("ids") Long ids,
                                        @RequestParam("recommendStatus") Integer recommendStatus) {
        int count = subjectService.updateRecommendStatus(ids, recommendStatus);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改展示状态")
    @RequestMapping(value = "/update/updateShowStatus")
    @ResponseBody
    @SysLog(MODULE = "cms", REMARK = "修改展示状态")
    public Object updateShowStatus(@RequestParam("ids") Long ids,
                                   @RequestParam("showStatus") Integer showStatus) {
        int count = subjectService.updateShowStatus(ids, showStatus);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }
}
