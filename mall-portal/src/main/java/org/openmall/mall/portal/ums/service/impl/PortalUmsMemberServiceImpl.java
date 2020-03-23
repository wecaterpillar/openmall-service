package org.openmall.mall.portal.ums.service.impl;

import org.openmall.mall.common.exception.Asserts;
import org.openmall.mall.portal.ums.service.PortalUmsMemberService;
import org.openmall.mall.ums.service.UmsMemberCacheService;
import org.openmall.mall.security.util.JwtTokenUtil;
import org.openmall.mall.security.util.SecurityUtil;
import org.openmall.mall.ums.domain.MemberDetails;
import org.openmall.mall.ums.mapper.UmsMemberLevelMapper;
import org.openmall.mall.ums.mapper.UmsMemberMapper;
import org.openmall.mall.ums.model.UmsMember;
import org.openmall.mall.ums.model.UmsMemberExample;
import org.openmall.mall.ums.model.UmsMemberLevel;
import org.openmall.mall.ums.model.UmsMemberLevelExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 会员管理Service实现类
 */
@Service
public class PortalUmsMemberServiceImpl implements PortalUmsMemberService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PortalUmsMemberServiceImpl.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    protected UmsMemberMapper memberMapper;
    @Autowired
    protected UmsMemberLevelMapper memberLevelMapper;


    //@Autowired
    //protected RedisService redisService;

    @Autowired
    private UmsMemberCacheService memberCacheService;


    @Value("${openmall.verify-auth-code:false}")
    protected boolean needVerifyAuthCode;

    @Value("${redis.key.authCode}")
    protected String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.expire.authCode}")
    protected Long AUTH_CODE_EXPIRE_SECONDS;




    @Override
    public void register(String username, String password, String telephone, String authCode) {
        //验证验证码
        if(needVerifyAuthCode && !verifyAuthCode(authCode,telephone)){
            Asserts.fail("验证码错误");
        }

        // TODO 需要增加快速注册的逻辑
        if(username==null || "".equals(username)){
            username = telephone;
        }

        //查询是否已有该用户
        UmsMemberExample example = new UmsMemberExample();
        example.createCriteria().andUsernameEqualTo(username);
        example.or(example.createCriteria().andPhoneEqualTo(telephone));
        List<UmsMember> umsMembers = memberMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(umsMembers)) {
            Asserts.fail("该用户已经存在");
        }
        //没有该用户进行添加操作
        UmsMember umsMember = new UmsMember();
        umsMember.setUsername(username);
        umsMember.setPhone(telephone);
        umsMember.setPassword(passwordEncoder.encode(password));
        umsMember.setCreateTime(new Date());
        umsMember.setStatus(1);
        //获取默认会员等级并设置
        UmsMemberLevelExample levelExample = new UmsMemberLevelExample();
        levelExample.createCriteria().andDefaultStatusEqualTo(1);
        List<UmsMemberLevel> memberLevelList = memberLevelMapper.selectByExample(levelExample);
        if (!CollectionUtils.isEmpty(memberLevelList)) {
            umsMember.setMemberLevelId(memberLevelList.get(0).getId());
        }
        memberMapper.insert(umsMember);
        umsMember.setPassword(null);
    }



    @Override
    public void updatePassword(String telephone, String password, String authCode) {
        UmsMemberExample example = new UmsMemberExample();
        example.createCriteria().andPhoneEqualTo(telephone);
        List<UmsMember> memberList = memberMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(memberList)){
            Asserts.fail("该账号不存在");
        }
        //验证验证码
        if(needVerifyAuthCode && !verifyAuthCode(authCode,telephone)){
            Asserts.fail("验证码错误");
        }
        UmsMember umsMember = memberList.get(0);
        umsMember.setPassword(passwordEncoder.encode(password));
        memberMapper.updateByPrimaryKeySelective(umsMember);
    }


    @Override
    public UmsMember getCurrentMember() {
        MemberDetails memberDetails = (MemberDetails)SecurityUtil.getCurrentUserDetails();
        return memberDetails.getUmsMember();
    }

    @Override
    public UmsMember getById(Long id) {
        return memberMapper.selectByPrimaryKey(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UmsMember member = getByUsername(username);
        if(member!=null){
            return new MemberDetails(member);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    private UmsMember getByUsername(String username) {
        UmsMember member = memberCacheService.getMember(username);
        if(member!=null) return member;
        UmsMemberExample example = new UmsMemberExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsMember> memberList = memberMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(memberList)) {
            member = memberList.get(0);
            memberCacheService.setMember(member);
            return member;
        }
        return null;
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


    @Override
    public void updateIntegration(Long id, Integer integration) {
        UmsMember record=new UmsMember();
        record.setId(id);
        record.setIntegration(integration);
        memberMapper.updateByPrimaryKeySelective(record);
        memberCacheService.delMember(id);
    }


    public String generateAuthCode(String telephone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i=0;i<6;i++){
            sb.append(random.nextInt(10));
        }
        //验证码绑定手机号并存储到redis
        memberCacheService.setAuthCode(telephone,sb.toString());
        //redisService.set(REDIS_KEY_PREFIX_AUTH_CODE+telephone,sb.toString());
        //redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE+telephone,AUTH_CODE_EXPIRE_SECONDS);
        return sb.toString();
    }


    //对输入的验证码进行校验
    public boolean verifyAuthCode(String authCode, String telephone){
        if(StringUtils.isEmpty(authCode)){
            return false;
        }
        String realAuthCode = memberCacheService.getAuthCode(telephone);
        //String realAuthCode = (String)redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + telephone);
        return authCode.equals(realAuthCode);
    }

}
