package org.openmall.mall.portal.ums.service;

import org.openmall.mall.ums.model.UmsMember;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 会员管理Service
 */
public interface UmsMemberLoginService {

    /**
     * 生成验证码
     */
    String generateAuthCode(String telephone);

    /**
     * 获取当前登录会员
     */
    UmsMember getCurrentMember();

    /**
     * 根据用户名获取会员
     */
    UserDetails loadUserByUsername(String username);
    /**
     * 登录后获取token
     */
    String login(String username, String password);

    /**
     * 刷新token
     */
    String refreshToken(String token);

    /**
     * 对输入的验证码进行校验
     * @param authCode
     * @param telephone
     */
    boolean verifyAuthCode(String authCode, String telephone);

}
