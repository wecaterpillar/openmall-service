package org.openmall.mall.portal.ums.service.impl;

import org.openmall.mall.portal.ums.service.UmsMemberLoginService;
import org.openmall.mall.security.util.JwtTokenUtil;
import org.openmall.mall.security.util.SecurityUtil;
import org.openmall.mall.ums.domain.MemberDetails;
import org.openmall.mall.ums.model.UmsMember;
import org.openmall.mall.ums.service.impl.UmsMemberServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 会员管理Service实现类
 */
@Service
public class UmsMemberLoginServiceImpl extends UmsMemberServiceImpl implements UmsMemberLoginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsMemberLoginServiceImpl.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Override
    public UmsMember getCurrentMember() {
        MemberDetails memberDetails = (MemberDetails)SecurityUtil.getCurrentUserDetails();
        return memberDetails.getUmsMember();
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UmsMember member = getByUsername(username);
        if(member!=null){
            return new MemberDetails(member);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }


    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            SecurityUtil.setCurrentUserDetails(userDetails);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public String refreshToken(String token) {
        return jwtTokenUtil.refreshHeadToken(token);
    }

}
