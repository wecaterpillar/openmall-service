package org.openmall.mall.portal.ums.service;

import org.openmall.mall.ums.model.UmsMember;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

/**
 * 会员管理Service
 */
public interface PortalUmsMemberService {

    /**
     * 获取当前登录会员
     */
    UmsMember getCurrentMember();

    /**
     * 根据会员编号获取会员
     */
    UmsMember getById(Long id);

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
     * 用户注册
     */
    @Transactional
    void register(String username, String password, String telephone, String authCode);


    /**
     * 修改密码
     */
    @Transactional
    void updatePassword(String telephone, String password, String authCode);


    /**
     * 根据会员id修改会员积分
     */
    void updateIntegration(Long id,Integer integration);


    /**
     * 生成验证码
     */
    String generateAuthCode(String telephone);

    /**
     * 对输入的验证码进行校验
     * @param authCode
     * @param telephone
     */
    boolean verifyAuthCode(String authCode, String telephone);

}
