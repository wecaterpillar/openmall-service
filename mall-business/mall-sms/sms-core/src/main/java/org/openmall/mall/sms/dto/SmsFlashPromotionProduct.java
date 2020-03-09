package org.openmall.mall.sms.dto;

//import org.openmall.mall.pms.model.PmsProduct;
import org.openmall.mall.sms.model.SmsFlashPromotionProductRelation;
import lombok.Getter;
import lombok.Setter;

/**
 * 限时购及商品信息封装
 */
public class SmsFlashPromotionProduct extends SmsFlashPromotionProductRelation {
    @Getter
    @Setter
    private PmsProduct product;
}
