package org.openmall.mall.pms.sync.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.openmall.mall.common.api.CommonResult;
import org.openmall.mall.pms.service.PmsProductService;
import org.openmall.mall.pms.sync.service.ProductSyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "ProductSyncController", description = "商品同步")
@RequestMapping("/pms/sync")
public class ProductSyncController {

    @Autowired
    private PmsProductService productService;

    @Autowired
    private ProductSyncService productSyncService;

    @ApiOperation(value = "导入Mongo数据中商品")
    @RequestMapping(value = "/importProduct", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> importProduct() {
        return CommonResult.success(productSyncService.importProduct());
    }
}
