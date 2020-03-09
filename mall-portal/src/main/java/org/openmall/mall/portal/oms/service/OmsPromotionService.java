package org.openmall.mall.portal.oms.service;

import org.openmall.mall.oms.model.OmsCartItem;
import org.openmall.mall.portal.oms.domain.CartPromotionItem;

import java.util.List;

/**
 * 促销管理Service
 */
public interface OmsPromotionService {
    /**
     * 计算购物车中的促销活动信息
     * @param cartItemList 购物车
     */
    List<CartPromotionItem> calcCartPromotion(List<OmsCartItem> cartItemList);
}
