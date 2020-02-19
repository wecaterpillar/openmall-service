package org.openmall.mall.ums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.openmall.mall.ums.mapper.UmsMemberMapper;
import org.openmall.mall.ums.model.UmsMember;
import org.openmall.mall.ums.service.UmsMemberService2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

/**
 * 会员管理Service实现类
 */
@Service
public class UmsMemberService2Impl extends ServiceImpl<UmsMemberMapper, UmsMember> implements UmsMemberService2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(UmsMemberService2Impl.class);

    @Autowired
    private UmsMemberMapper memberMapper;

    @Override
    public void updataMemberOrderInfo() {
        // TODO
    }
}
