package org.openmall.mall.portal.pms.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.openmall.mall.common.api.CommonPage;
import org.openmall.mall.common.api.CommonResult;
import org.openmall.mall.pms.dto.PmsProductQueryParam;
import org.openmall.mall.pms.dto.PmsProductResult;
import org.openmall.mall.pms.model.PmsProduct;
import org.openmall.mall.portal.pms.service.PortalPmsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 商品查询Controller
 */
@Controller
@Api(tags = "PortalPmsProductService", description = "商品管理")
@RequestMapping("/pms/product")
public class PortalPmsProductController {
    @Autowired
    private PortalPmsProductService productService;

    @ApiOperation("根据商品id获取商品编辑信息")
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsProductResult> getProduct(@PathVariable Long id) {
        PmsProductResult productResult = productService.getProductById(id);
        return CommonResult.success(productResult);
    }

    @ApiOperation("查询商品")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsProduct>> getList(PmsProductQueryParam productQueryParam,
                                                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<PmsProduct> productList = productService.list(productQueryParam, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(productList));
    }

    @ApiOperation("根据商品名称或货号模糊查询")
    @RequestMapping(value = "/listByName", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsProduct>> getList( @RequestParam(value = "keyword") String keyword) {
        List<PmsProduct> productList = productService.list(keyword);
        return CommonResult.success(productList);
    }

}
