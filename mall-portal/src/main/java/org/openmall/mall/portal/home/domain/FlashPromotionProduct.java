package org.openmall.mall.portal.home.domain;

import org.openmall.mall.pms.model.PmsProduct;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 秒杀信息和商品对象封装
 */
@Getter
@Setter
public class FlashPromotionProduct extends PmsProduct {
    private BigDecimal flashPromotionPrice;
    private Integer flashPromotionCount;
    private Integer flashPromotionLimit;
}
