package org.openmall.mall.portal.oms.service;

import org.openmall.mall.portal.oms.domain.OmsOrderReturnApplyParam;

/**
 * 订单退货管理Service
 */
public interface OmsPortalOrderReturnApplyService {
    /**
     * 提交申请
     */
    int create(OmsOrderReturnApplyParam returnApply);
}
