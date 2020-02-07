package org.openmall.mall.ums.service.impl;

import org.openmall.mall.ums.mapper.UmsMemberMapper;
import org.openmall.mall.ums.model.UmsMember;
import org.openmall.mall.ums.service.UmsMemberService2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 会员管理Service实现类
 */
@Service
public class UmsMemberService2Impl implements UmsMemberService2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(UmsMemberService2Impl.class);

    @Autowired
    private UmsMemberMapper memberMapper;


    public List<UmsMember> listAll(){
        return memberMapper.selectByExample(null);
    }

}
