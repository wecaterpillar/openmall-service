package org.openmall.mall.portal.pms.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.openmall.mall.common.api.CommonPage;
import org.openmall.mall.common.api.CommonResult;
import org.openmall.mall.pms.dto.PmsProductCategoryWithChildrenItem;
import org.openmall.mall.pms.model.PmsProductCategory;
import org.openmall.mall.portal.pms.service.PortalPmsProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品分类模块Controller
 */
@Controller
@Api(tags = "PortalPmsProductCategoryController", description = "商品分类管理")
@RequestMapping("/pms/productCategory")
public class PortalPmsProductCategoryController {
    @Autowired
    private PortalPmsProductCategoryService productCategoryService;

    @ApiOperation("分页查询商品分类")
    @RequestMapping(value = "/list/{parentId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsProductCategory>> getList(@PathVariable Long parentId,
                                                                @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<PmsProductCategory> productCategoryList = productCategoryService.getList(parentId, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(productCategoryList));
    }

    @ApiOperation("查询所有一级分类及子分类")
    @RequestMapping(value = "/list/withChildren", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsProductCategoryWithChildrenItem>> listWithChildren() {
        List<PmsProductCategoryWithChildrenItem> list = productCategoryService.listWithChildren();
        return CommonResult.success(list);
    }
}
