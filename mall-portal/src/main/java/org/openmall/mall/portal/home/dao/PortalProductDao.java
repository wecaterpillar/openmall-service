package org.openmall.mall.portal.home.dao;

import org.openmall.mall.portal.oms.domain.CartProduct;
import org.openmall.mall.portal.home.domain.PromotionProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 前台系统自定义商品Dao
 */
public interface PortalProductDao {
    CartProduct getCartProduct(@Param("id") Long id);
    List<PromotionProduct> getPromotionProductList(@Param("ids") List<Long> ids);
}
