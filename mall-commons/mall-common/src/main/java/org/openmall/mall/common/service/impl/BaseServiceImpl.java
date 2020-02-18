package org.openmall.mall.common.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.openmall.mall.common.service.IBaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("baseServiceImplMP")
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements IBaseService<T> {

}
