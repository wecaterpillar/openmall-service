package org.openmall.mall.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.openmall.mall.cms.mapper.CmsTopicCategoryMapper;
import org.openmall.mall.cms.mapper.CmsTopicMapper;
import org.openmall.mall.cms.model.CmsTopic;
import org.openmall.mall.cms.model.CmsTopicCategory;
import org.openmall.mall.cms.service.CmsTopicService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class CmsTopicServiceImpl extends ServiceImpl<CmsTopicMapper, CmsTopic> implements CmsTopicService {

    @Resource
    private CmsTopicMapper topicMapper;

//    @Resource
//    private CmsTopicMemberMapper topicMemberMapper;

    @Resource
    private CmsTopicCategoryMapper topicCategoryMapper;

    @Override
    @Transactional
    public boolean saves(CmsTopic entity) {
        entity.setCreateTime(new Date());
        topicMapper.insert(entity);
        CmsTopicCategory category = topicCategoryMapper.selectById(entity.getCategoryId());
        category.setSubjectCount(category.getSubjectCount()==null?0:category.getSubjectCount() + 1);
        topicCategoryMapper.updateById(category);
        return true;
    }

    @Transactional
    @Override
    public int updateVerifyStatus(Long ids, Long topicId, Integer verifyStatus) {
        int count = 0;
//        CmsTopicMember product = new CmsTopicMember();
//        product.setStatus(verifyStatus + "");
//        product.setId(ids);
//        int count = topicMemberMapper.updateById(product);

        CmsTopic topic = topicMapper.selectById(topicId);
        if (verifyStatus == 1) {
            topic.setAttendCount(topic.getAttendCount() + 1);
            count = topic.getAttendCount() + 1;
        } else {
            topic.setAttendCount(topic.getAttendCount() - 1);
            count = topic.getAttendCount() - 1;
        }
        topicMapper.updateById(topic);
        return count;
    }
}
