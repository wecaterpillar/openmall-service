package org.openmall.mall.pms.service.impl;

import org.openmall.mall.pms.mapper.CmsPrefrenceAreaMapper;
import org.openmall.mall.pms.model.CmsPrefrenceArea;
import org.openmall.mall.pms.model.CmsPrefrenceAreaExample;
import org.openmall.mall.pms.service.CmsPrefrenceAreaService;
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
