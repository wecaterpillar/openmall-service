package org.openmall.mall.ums.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.openmall.mall.common.api.CommonResult;
import org.openmall.mall.ums.model.UmsMember;
import org.openmall.mall.ums.service.UmsMemberService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 会员登录注册管理Controller
 */
@Controller
@Api(tags = "UmsMemberController", description = "会员管理")
@RequestMapping("/ums/member")
public class UmsMemberController2 {


    @Autowired
    private UmsMemberService2 memberService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation("查询所有会员")
    @ResponseBody
    public CommonResult<List<UmsMember>> list() {
        //TODO 增加分页
        List<UmsMember> memberList = memberService.listAll();
        return CommonResult.success(memberList);
    }
}
