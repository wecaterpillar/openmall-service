package org.openmall.mall.portal.ums.service;

import org.openmall.mall.common.api.CommonResult;
import org.openmall.mall.portal.oms.domain.CartPromotionItem;
import org.openmall.mall.sms.model.SmsCouponHistory;
import org.openmall.mall.portal.ums.domain.SmsCouponHistoryDetail;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户优惠券管理Service
 */
public interface UmsMemberCouponService {
    /**
     * 会员添加优惠券
     */
    @Transactional
    CommonResult add(Long couponId);

    /**
     * 获取优惠券列表
     * @param useStatus 优惠券的使用状态
     */
    List<SmsCouponHistory> list(Integer useStatus);

    /**
     * 根据购物车信息获取可用优惠券
     */
    List<SmsCouponHistoryDetail> listCart(List<CartPromotionItem> cartItemList, Integer type);
}
