package org.openmall.mall.cms.service;

import org.openmall.mall.cms.model.CmsSubject;
import org.openmall.mall.common.service.IBaseService;

import java.util.List;

/**
 * 商品专题Service
 */
public interface CmsSubjectService extends IBaseService<CmsSubject> {
    /**
     * 查询所有专题
     */
    List<CmsSubject> listAll();

    /**
     * 分页查询专题
     */
    List<CmsSubject> list(String keyword, Integer pageNum, Integer pageSize);


    int updateRecommendStatus(Long ids, Integer recommendStatus);

    int updateShowStatus(Long ids, Integer showStatus);

    boolean saves(CmsSubject entity);
}
