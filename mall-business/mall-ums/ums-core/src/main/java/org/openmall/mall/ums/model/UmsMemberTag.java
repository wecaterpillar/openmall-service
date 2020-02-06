package org.openmall.mall.ums.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 会员标签
 */
@lombok.Data
public class UmsMemberTag implements Serializable {
    private Long id;

    private String name;

    @ApiModelProperty(value = "自动打标签完成订单数量")
    private Integer finishOrderCount;

    @ApiModelProperty(value = "自动打标签完成订单金额")
    private BigDecimal finishOrderAmount;

    private static final long serialVersionUID = 1L;


}
