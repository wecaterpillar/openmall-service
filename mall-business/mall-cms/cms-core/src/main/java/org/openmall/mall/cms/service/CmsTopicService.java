package org.openmall.mall.cms.service;

import org.openmall.mall.cms.model.CmsTopic;
import org.openmall.mall.common.service.IBaseService;

public interface CmsTopicService extends IBaseService<CmsTopic> {

    int updateVerifyStatus(Long ids, Long topicId, Integer verifyStatus);

    boolean saves(CmsTopic entity);
}
