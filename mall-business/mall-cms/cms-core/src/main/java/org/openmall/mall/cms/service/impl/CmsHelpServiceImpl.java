package org.openmall.mall.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.openmall.mall.cms.mapper.CmsHelpCategoryMapper;
import org.openmall.mall.cms.mapper.CmsHelpMapper;
import org.openmall.mall.cms.model.CmsHelp;
import org.openmall.mall.cms.model.CmsHelpCategory;
import org.openmall.mall.cms.service.CmsHelpService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class CmsHelpServiceImpl extends ServiceImpl<CmsHelpMapper, CmsHelp>
        implements CmsHelpService {

    @Resource
    private CmsHelpMapper helpMapper;
    @Resource
    private CmsHelpCategoryMapper helpCategoryMapper;


    @Override
    @Transactional
    public boolean saves(CmsHelp entity) {
        entity.setCreateTime(new Date());
        helpMapper.insert(entity);
        CmsHelpCategory category = helpCategoryMapper.selectById(entity.getCategoryId());
        category.setHelpCount(category.getHelpCount() + 1);
        helpCategoryMapper.updateById(category);
        return true;
    }
}
