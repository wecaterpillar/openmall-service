package org.openmall.mall.portal.ums.controller;

import org.openmall.mall.common.api.CommonResult;
import org.openmall.mall.portal.ums.domain.MemberDto;
import org.openmall.mall.portal.ums.service.UmsMemberLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.openmall.mall.ums.model.UmsMember;
import org.openmall.mall.ums.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 会员登录注册管理Controller
 */
@Controller
@Api(tags = "UmsMemberController", description = "会员登录注册管理")
@RequestMapping("/sso")
public class UmsMemberController {
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UmsMemberLoginService memberLoginService;
    @Autowired
    private UmsMemberService memberService;

    @ApiOperation("会员注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult register(@RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam String telephone,
                                 @RequestParam String authCode) {
        //验证验证码
        if(!memberLoginService.verifyAuthCode(authCode,telephone)){
            return CommonResult.failed("验证码错误");
        }
        return memberService.register(username, password, telephone);
    }

    @ApiOperation("会员登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestParam String username,
                              @RequestParam String password) {
        String token = memberLoginService.login(username, password);
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation("获取验证码")
    @RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAuthCode(@RequestParam String telephone) {
        return memberLoginService.generateAuthCode(telephone);
    }

    @ApiOperation("修改密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updatePassword(@RequestParam String telephone,
                                 @RequestParam String password,
                                 @RequestParam String authCode) {
        //验证验证码
        if(!memberLoginService.verifyAuthCode(authCode,telephone)){
            return CommonResult.failed("验证码错误");
        }
        return memberService.updatePassword(telephone,password);
    }

    @ApiOperation("当前用户信息")
    @RequestMapping(value = "/current", method = RequestMethod.GET)
    @ResponseBody
    public MemberDto getCurrent(){
        final UmsMember currentMember = memberLoginService.getCurrentMember();
        MemberDto member = new MemberDto();
        member.setId(currentMember.getId());
        member.setUsername(currentMember.getUsername());
        member.setPhone(currentMember.getPhone());
        member.setMemberLevelId(currentMember.getMemberLevelId());
        member.setNickname(currentMember.getNickname());
        member.setIcon(currentMember.getIcon());
        return member;
    }

    @ApiOperation(value = "刷新token")
    @RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = memberLoginService.refreshToken(token);
        if (refreshToken == null) {
            return CommonResult.failed("token已经过期！");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }
}
