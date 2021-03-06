package org.openmall.mall.ums.service;

import org.openmall.mall.common.service.IBaseService;
import org.openmall.mall.ums.model.UmsMemberLevel;

import java.util.List;

/**
 * 会员等级管理Service
 */
public interface UmsMemberLevelService extends IBaseService<UmsMemberLevel> {
    /**
     * 获取所有会员登录
     * @param defaultStatus 是否为默认会员
     */
    List<UmsMemberLevel> list(Integer defaultStatus);

}
