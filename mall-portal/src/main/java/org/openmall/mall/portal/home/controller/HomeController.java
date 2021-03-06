package org.openmall.mall.portal.home.controller;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.openmall.mall.cms.model.CmsHelp;
import org.openmall.mall.cms.model.CmsSubject;
import org.openmall.mall.cms.model.CmsTopic;
import org.openmall.mall.common.api.CommonResult;
import org.openmall.mall.pms.model.PmsProduct;
import org.openmall.mall.pms.model.PmsProductCategory;
import org.openmall.mall.portal.home.domain.HomeContentResult;
import org.openmall.mall.portal.home.domain.HomeLayerContent;
import org.openmall.mall.portal.home.domain.NavProductCategory;
import org.openmall.mall.portal.home.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页内容管理Controller
 *
 *    TODO 增加接口获取网站的属性信息
 *    TODO 增加接口获取网站的导航分类以及子分类信息
 */
@Controller
@Api(tags = "HomeController", description = "首页内容管理")
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private HomeService homeService;

    @ApiOperation("获取网站配置信息")
    @RequestMapping(value = "/config", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Map<String,Object>>  config() {
        return CommonResult.success(loadWebsiteConfig());
    }

    private Map<String,Object> loadWebsiteConfig(){
        Map<String,Object> websiteConfig = new HashMap<>();

        //  读取配置文件
        JSON jsonObj = JSONUtil.parse(ResourceUtil.readUtf8Str("classpath:config/webconfig.json"));

        // layers
        List<HomeLayerContent> layers = null;
        try {
            layers = JSONUtil.toList(JSONUtil.parseArray(jsonObj.getByPath("layers")), HomeLayerContent.class);
        }catch (Exception e){
        }
        if(layers==null){
            layers = new ArrayList<>();
            layers.add(new HomeLayerContent(1, "now-trending", 1,2,"7"));
            layers.add(new HomeLayerContent(2, "just-in", 11,12,""));
            layers.add(new HomeLayerContent(3, "featured", 1,4,"1"));
            layers.add(new HomeLayerContent(4, "editor-picks", 1,4,"6"));
            layers.add(new HomeLayerContent(5, "auth", 4,2,"11,12"));
            layers.add(new HomeLayerContent(6, "blog", 2,3,"2"));
        }
        websiteConfig.put("layers", layers);
        // props
        websiteConfig.put("props", jsonObj.getByPath("props"));

        websiteConfig.putAll(homeService.getChannelConfig());
        return websiteConfig;
    }

    @ApiOperation("首页内容页信息展示")
    @RequestMapping(value = "/content", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<HomeContentResult> content() {
        Map<String,Object> websiteConfig = loadWebsiteConfig();
        HomeContentResult contentResult = homeService.content(websiteConfig);
        return CommonResult.success(contentResult);
    }

    @ApiOperation("获取导航栏商品分类")
    @RequestMapping(value = "/navProductCateList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<NavProductCategory>> getNavProductCateList() {
        Map<String,Object> websiteConfig = loadWebsiteConfig();
        List<NavProductCategory> productCategoryList = homeService.getNavProductCateList(websiteConfig);
        return CommonResult.success(productCategoryList);
    }

    @ApiOperation("分页获取推荐商品")
    @RequestMapping(value = "/recommendProductList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsProduct>> recommendProductList(@RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize,
                                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<PmsProduct> productList = homeService.recommendProductList(pageSize, pageNum);
        return CommonResult.success(productList);
    }

    @ApiOperation("获取首页商品分类")
    @RequestMapping(value = "/productCateList/{parentId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsProductCategory>> getProductCateList(@PathVariable Long parentId) {
        List<PmsProductCategory> productCategoryList = homeService.getProductCateList(parentId);
        return CommonResult.success(productCategoryList);
    }



    @ApiOperation("根据分类获取专题")
    @RequestMapping(value = "/subjectList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<CmsSubject>> getSubjectList(@RequestParam(required = false) Long cateId) {
        List<CmsSubject> subjectList = homeService.getSubjectList(cateId);
        return CommonResult.success(subjectList);
    }

    @ApiOperation("根据分类获取话题")
    @RequestMapping(value = "/topicList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<CmsTopic>> getTopicList(@RequestParam(required = false) Long cateId,
                                                     @RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize,
                                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<CmsTopic> topicList = homeService.getTopicList(cateId,pageSize,pageNum);
        return CommonResult.success(topicList);
    }

    @ApiOperation("根据分类获取帮助")
    @RequestMapping(value = "/helpList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<CmsHelp>> getHelpList(@RequestParam(required = false) Long cateId,
                                                   @RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<CmsHelp> helpList = homeService.getHelpList(cateId,pageSize,pageNum);
        return CommonResult.success(helpList);
    }


}
