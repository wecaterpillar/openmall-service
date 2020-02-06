package org.openmall.mall.pms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品会员价格
 *
 * 根据不同会员等级，可以以不同的会员价格购买。此处设计有缺陷，可以做成不同会员等级可以减免多少元或者按多少折扣进行购买。
 */
@lombok.Data
public class PmsMemberPrice implements Serializable {
    private Long id;

    @ApiModelProperty(value = "商品id")
    private Long productId;

    @ApiModelProperty(value = "会员等级id")
    private Long memberLevelId;

    @ApiModelProperty(value = "会员价格")
    private BigDecimal memberPrice;

    @ApiModelProperty(value = "会员等级名称")
    private String memberLevelName;

    private static final long serialVersionUID = 1L;


}
