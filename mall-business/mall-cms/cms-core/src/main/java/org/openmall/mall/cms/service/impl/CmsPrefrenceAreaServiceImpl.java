package org.openmall.mall.cms.service.impl;

import org.openmall.mall.cms.mapper.CmsPrefrenceAreaMapper;
import org.openmall.mall.cms.model.CmsPrefrenceArea;
import org.openmall.mall.cms.model.CmsPrefrenceAreaExample;
import org.openmall.mall.cms.service.CmsPrefrenceAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品优选Service实现类
 * Created by macro on 2018/6/1.
 */
@Service
public class CmsPrefrenceAreaServiceImpl implements CmsPrefrenceAreaService {
    @Autowired
    private CmsPrefrenceAreaMapper prefrenceAreaMapper;

    @Override
    public List<CmsPrefrenceArea> listAll() {
        return prefrenceAreaMapper.selectByExample(new CmsPrefrenceAreaExample());
    }
}
