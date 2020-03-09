package org.openmall.mall.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.openmall.mall.cms.mapper.CmsTopicCategoryMapper;
import org.openmall.mall.cms.model.CmsTopicCategory;
import org.openmall.mall.cms.service.CmsTopicCategoryService;
import org.springframework.stereotype.Service;

@Service
public class CmsTopicCategoryServiceImpl
        extends ServiceImpl<CmsTopicCategoryMapper, CmsTopicCategory>
        implements CmsTopicCategoryService {
}
