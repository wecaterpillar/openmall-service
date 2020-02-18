package org.openmall.mall.ums.service.impl;

import org.openmall.mall.common.service.impl.BaseServiceImpl;
import org.openmall.mall.ums.mapper.UmsMemberLevelMapper;
import org.openmall.mall.ums.model.UmsMemberLevel;
import org.openmall.mall.ums.model.UmsMemberLevelExample;
import org.openmall.mall.ums.service.UmsMemberLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会员等级管理Service实现类
 */
@Service
public class UmsMemberLevelServiceImpl extends BaseServiceImpl<UmsMemberLevelMapper,UmsMemberLevel> implements UmsMemberLevelService{
    @Autowired
    private UmsMemberLevelMapper memberLevelMapper;
    @Override
    public List<UmsMemberLevel> list(Integer defaultStatus) {
        UmsMemberLevelExample example = new UmsMemberLevelExample();
        if(defaultStatus!=null){
            example.createCriteria().andDefaultStatusEqualTo(defaultStatus);
        }
        return memberLevelMapper.selectByExample(example);
    }
}
