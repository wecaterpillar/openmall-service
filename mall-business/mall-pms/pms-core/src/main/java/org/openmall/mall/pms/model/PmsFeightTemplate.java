package org.openmall.mall.pms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 运费模板
 */
@lombok.Data
public class PmsFeightTemplate implements Serializable {
    private Long id;

    private String name;

    @ApiModelProperty(value = "计费类型:0->按重量；1->按件数")
    private Integer chargeType;

    @ApiModelProperty(value = "首重kg")
    private BigDecimal firstWeight;

    @ApiModelProperty(value = "首费（元）")
    private BigDecimal firstFee;

    @ApiModelProperty(value = "续重kg")
    private BigDecimal continueWeight;

    @ApiModelProperty(value = "续费元")
    private BigDecimal continmeFee;

    @ApiModelProperty(value = "目的地（省、市）")
    private String dest;

    private static final long serialVersionUID = 1L;


}
