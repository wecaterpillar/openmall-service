package org.openmall.mall.ums.service;

import org.openmall.mall.common.service.IBaseService;
import org.openmall.mall.ums.model.UmsMember;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 会员管理Service
 */
public interface UmsMemberService extends IBaseService<UmsMember> {

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


    /**
     * 根据用户名获取会员
     */
    UmsMember getByUsername(String username);

    /**
     * 根据会员编号获取会员
     */
    UmsMember getById(Long id);

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


    List<UmsMember> listAll();

    void updataMemberOrderInfo();

}
