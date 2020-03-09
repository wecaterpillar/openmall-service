package org.openmall.mall.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.openmall.mall.cms.mapper.CmsPrefrenceAreaMapper;
import org.openmall.mall.cms.model.CmsPrefrenceArea;
import org.openmall.mall.cms.model.CmsPrefrenceAreaExample;
import org.openmall.mall.cms.service.CmsPrefrenceAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品优选Service实现类
 */
@Service
public class CmsPrefrenceAreaServiceImpl extends ServiceImpl<CmsPrefrenceAreaMapper, CmsPrefrenceArea> implements CmsPrefrenceAreaService {
    @Autowired
    private CmsPrefrenceAreaMapper prefrenceAreaMapper;

    @Override
    public List<CmsPrefrenceArea> listAll() {
        return prefrenceAreaMapper.selectByExample(new CmsPrefrenceAreaExample());
    }
}
