package org.openmall.mall.ums.service;

import org.openmall.mall.common.api.CommonResult;
import org.openmall.mall.common.service.IBaseService;
import org.openmall.mall.ums.model.UmsMember;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 会员管理Service
 */
public interface UmsMemberService2 extends IBaseService<UmsMember> {

    void updataMemberOrderInfo();

}
