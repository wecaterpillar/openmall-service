package org.openmall.mall.sms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 限时购与商品关系
 *
 * 用于存储与限时购相关的商品信息，一个限时购中有多个场次，每个场次都可以设置不同活动商品。
 */
@lombok.Data
public class SmsFlashPromotionProductRelation implements Serializable {
    @ApiModelProperty(value = "编号")
    private Long id;

    @ApiModelProperty(value = "限时购id")
    private Long flashPromotionId;

    @ApiModelProperty(value = "场次编号")
    private Long flashPromotionSessionId;

    private Long productId;

    @ApiModelProperty(value = "限时购价格")
    private BigDecimal flashPromotionPrice;

    @ApiModelProperty(value = "限时购数量")
    private Integer flashPromotionCount;

    @ApiModelProperty(value = "每人限购数量")
    private Integer flashPromotionLimit;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    private static final long serialVersionUID = 1L;


}
