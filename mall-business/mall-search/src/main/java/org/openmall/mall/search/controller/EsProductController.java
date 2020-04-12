package org.openmall.mall.search.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.openmall.mall.common.api.CommonPage;
import org.openmall.mall.common.api.CommonResult;
import org.openmall.mall.search.domain.EsProduct;
import org.openmall.mall.search.domain.EsProductRelatedInfo;
import org.openmall.mall.search.dto.AggVO;
import org.openmall.mall.search.dto.QueryVO;
import org.openmall.mall.search.service.EsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 搜索商品管理Controller
 */
@RestController
@Api(tags = "EsProductController", description = "搜索商品管理")
@RequestMapping("/search/esProduct")
public class EsProductController {
    @Autowired
    private EsProductService esProductService;

    @ApiOperation(value = "导入所有数据库中商品到ES")
    @RequestMapping(value = "/importAll", method = RequestMethod.POST)
    public CommonResult<Integer> importAllList() {
        int count = esProductService.importAll();
        return CommonResult.success(count);
    }

    @ApiOperation(value = "根据id删除商品")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult<Object> delete(@PathVariable Long id) {
        esProductService.delete(id);
        return CommonResult.success(null);
    }

    @ApiOperation(value = "根据id批量删除商品")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    public CommonResult<Object> delete(@RequestParam("ids") List<Long> ids) {
        esProductService.delete(ids);
        return CommonResult.success(null);
    }

    @ApiOperation(value = "根据id创建商品")
    @RequestMapping(value = "/create/{id}", method = RequestMethod.POST)
    public CommonResult<EsProduct> create(@PathVariable Long id) {
        EsProduct esProduct = esProductService.create(id);
        if (esProduct != null) {
            return CommonResult.success(esProduct);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "简单搜索")
    @RequestMapping(value = "/search/simple", method = RequestMethod.GET)
    public CommonResult<CommonPage<EsProduct>> search(@RequestParam(required = false) String keyword,
                                                      @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                      @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        Page<EsProduct> esProductPage = esProductService.search(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(esProductPage));
    }

    @ApiOperation(value = "综合搜索、筛选、排序")
    @ApiImplicitParam(name = "sort", value = "排序字段:0->按相关度；1->按新品；2->按销量；3->价格从低到高；4->价格从高到低",
            defaultValue = "0", allowableValues = "0,1,2,3,4", paramType = "query", dataType = "integer")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public CommonResult<CommonPage<EsProduct>> search(@RequestParam(required = false) String keyword,
                                                      @RequestParam(required = false) Long brandId,
                                                      @RequestParam(required = false) Long productCategoryId,
                                                      @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                      @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                                                      @RequestParam(required = false, defaultValue = "0") Integer sort) {
        Page<EsProduct> esProductPage = esProductService.search(keyword, brandId, productCategoryId, pageNum, pageSize, sort);
        return CommonResult.success(CommonPage.restPage(esProductPage));
    }

    @ApiOperation(value = "根据商品id推荐商品")
    @RequestMapping(value = "/recommend/{id}", method = RequestMethod.GET)
    public CommonResult<CommonPage<EsProduct>> recommend(@PathVariable Long id,
                                                         @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                         @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        Page<EsProduct> esProductPage = esProductService.recommend(id, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(esProductPage));
    }

    @ApiOperation(value = "获取搜索的相关品牌、分类及筛选属性")
    @RequestMapping(value = "/search/relate", method = RequestMethod.GET)
    public CommonResult<EsProductRelatedInfo> searchRelatedInfo(@RequestParam(required = false) String keyword) {
        EsProductRelatedInfo productRelatedInfo = esProductService.searchRelatedInfo(keyword);
        return CommonResult.success(productRelatedInfo);
    }

    /**
     * @Title: 聚合条件查询
     * @Param product
     * @Return: 返回类似京东侧边栏查询条件数据
     * @Description: 输入关键词或增加筛选条件逐步聚合出更精准的侧边栏筛选条件，如：有哪些分类、品牌、和规格参数可选
     */
    @ApiOperation(value = "聚合条件查询")
    @PostMapping("/agg")
    public CommonResult<AggVO> agg(@RequestBody QueryVO query) {
        return CommonResult.success(esProductService.agg(query));
    }


    /**
     * @Title: 模拟京东商品条件筛选查询
     * @Return: 返回商品查询的分页列表
     * @Description: 点击查询条件确定后-查询符合条件的商品。后续可增加排序规则
     */
    @ApiOperation(value = "条件筛选查询")
    @PostMapping("/search")
    public CommonResult<CommonPage<EsProduct>> search(@RequestBody QueryVO query,
                                @RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "10") int size) {
        Page<EsProduct> pages = esProductService.search(query, page, size);
        return  CommonResult.success(CommonPage.restPage(pages));
    }
}
