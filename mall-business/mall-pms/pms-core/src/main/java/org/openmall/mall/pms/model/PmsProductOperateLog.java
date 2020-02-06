package org.openmall.mall.pms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品操作记录
 *
 * 用于记录商品操作记录
 */
@lombok.Data
public class PmsProductOperateLog implements Serializable {
    private Long id;

    private Long productId;

    private BigDecimal priceOld;

    private BigDecimal priceNew;

    private BigDecimal salePriceOld;

    private BigDecimal salePriceNew;

    @ApiModelProperty(value = "赠送的积分")
    private Integer giftPointOld;

    private Integer giftPointNew;

    private Integer usePointLimitOld;

    private Integer usePointLimitNew;

    @ApiModelProperty(value = "操作人")
    private String operateMan;

    private Date createTime;

    private static final long serialVersionUID = 1L;

}
