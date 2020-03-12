package org.openmall.mall.portal.ums.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.openmall.mall.common.api.CommonResult;
import org.openmall.mall.portal.ums.domain.MemberDto;
import org.openmall.mall.portal.ums.service.UmsMemberLoginService;
import org.openmall.mall.ums.model.UmsMember;
import org.openmall.mall.ums.service.UmsMemberService;
import org.openmall.mall.ums.util.MemberSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 会员登录注册管理Controller
 */
@RestController
@Api(tags = "UmsMemberLoginController", description = "会员登录注册管理")
@RequestMapping("/sso")
public class UmsMemberLoginController {
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
    public CommonResult register(@RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam String telephone,
                                 @RequestParam String authCode) {
        memberService.register(username, password, telephone, authCode);
        return CommonResult.success(null,"注册成功");
    }

    @ApiOperation("会员登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
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
    public CommonResult getAuthCode(@RequestParam String telephone) {
        String authCode = memberLoginService.generateAuthCode(telephone);
        return CommonResult.success(authCode,"获取验证码成功");
    }

    @ApiOperation("修改密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public CommonResult updatePassword(@RequestParam String telephone,
                                 @RequestParam String password,
                                 @RequestParam String authCode) {
        memberService.updatePassword(telephone,password,authCode);
        return CommonResult.success(null,"密码修改成功");
    }

    @ApiOperation("当前用户信息")
    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public MemberDto getCurrent(){
        final UmsMember currentMember = MemberSecurityUtil.getCurrentMember();
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
