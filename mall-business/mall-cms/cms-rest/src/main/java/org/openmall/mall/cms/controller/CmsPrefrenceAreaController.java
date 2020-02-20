package org.openmall.mall.cms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.openmall.mall.common.annotation.SysLog;
import org.openmall.mall.common.api.CommonResult;
import org.openmall.mall.cms.model.CmsPrefrenceArea;
import org.openmall.mall.cms.service.CmsPrefrenceAreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.openmall.mall.validator.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品优选管理Controller
 */
@Slf4j
@RestController
@Api(tags = "CmsPrefrenceAreaController", description = "商品优选管理")
@RequestMapping("/cms/prefrenceArea")
public class CmsPrefrenceAreaController {
    @Autowired
    private CmsPrefrenceAreaService prefrenceAreaService;

    @ApiOperation("获取所有商品优选")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<CmsPrefrenceArea>> listAll() {
        List<CmsPrefrenceArea> prefrenceAreaList = prefrenceAreaService.listAll();
        return CommonResult.success(prefrenceAreaList);
    }

    @SysLog(MODULE = "cms", REMARK = "根据条件查询所有优选专区列表")
    @ApiOperation("根据条件查询所有优选专区列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('cms:prefrenceArea:read')")
    public Object getCmsPrefrenceAreaByPage(CmsPrefrenceArea entity,
                                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return CommonResult.success(prefrenceAreaService.page(new Page<CmsPrefrenceArea>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有优选专区列表：%s", e.getMessage(), e);
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "保存优选专区")
    @ApiOperation("保存优选专区")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('cms:prefrenceArea:create')")
    public Object saveCmsPrefrenceArea(@RequestBody CmsPrefrenceArea entity) {
        try {
            if (prefrenceAreaService.save(entity)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("保存优选专区：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "更新优选专区")
    @ApiOperation("更新优选专区")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('cms:prefrenceArea:update')")
    public Object updateCmsPrefrenceArea(@RequestBody CmsPrefrenceArea entity) {
        try {
            if (prefrenceAreaService.updateById(entity)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("更新优选专区：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "删除优选专区")
    @ApiOperation("删除优选专区")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('cms:prefrenceArea:delete')")
    public Object deleteCmsPrefrenceArea(@ApiParam("优选专区id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return CommonResult.paramFailed("优选专区id");
            }
            if (prefrenceAreaService.removeById(id)) {
                return CommonResult.success();
            }
        } catch (Exception e) {
            log.error("删除优选专区：%s", e.getMessage(), e);
            return CommonResult.failed();
        }
        return CommonResult.failed();
    }

    @SysLog(MODULE = "cms", REMARK = "给优选专区分配优选专区")
    @ApiOperation("查询优选专区明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('cms:prefrenceArea:read')")
    public Object getCmsPrefrenceAreaById(@ApiParam("优选专区id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return CommonResult.paramFailed("优选专区id");
            }
            CmsPrefrenceArea coupon = prefrenceAreaService.getById(id);
            return CommonResult.success(coupon);
        } catch (Exception e) {
            log.error("查询优选专区明细：%s", e.getMessage(), e);
            return CommonResult.failed();
        }

    }

    @ApiOperation(value = "批量删除优选专区")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.GET)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除优选专区")
    @PreAuthorize("hasAuthority('cms:prefrenceArea:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = prefrenceAreaService.removeByIds(ids);
        if (count) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }
}
