package org.openmall.mall.oms.service;

import org.openmall.mall.oms.domain.OmsOrderReturnApplyParam;

/**
 * 订单退货管理Service
 */
public interface OmsPortalOrderReturnApplyService {
    /**
     * 提交申请
     */
    int create(OmsOrderReturnApplyParam returnApply);
}
