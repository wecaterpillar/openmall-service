package org.openmall.mall.portal.pms.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.openmall.mall.common.api.CommonResult;
import org.openmall.mall.pms.model.PmsProductAttribute;
import org.openmall.mall.portal.pms.service.PortalPmsProductAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品类型模块Controller
 */
@Controller
@Api(tags = "PortalPmsProductAttributeController", description = "商品分类管理")
@RequestMapping("/pms/productAttribute")
public class PortalPmsProductAttributeController {
    @Autowired
    private PortalPmsProductAttributeService productAttributeService;

    @ApiOperation("根据分类查询属性列表和参数列表")
    @RequestMapping(value = "/list/{cid}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsProductAttribute>> getList(@PathVariable Long cid) {
        List<PmsProductAttribute> productAttributeList = productAttributeService.getList(cid);
        return CommonResult.success(productAttributeList);
    }
}
