package org.openmall.mall.oms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * 退货类型/退货理由
 */
@lombok.Data
public class OmsOrderReturnReason implements Serializable {
    private Long id;

    @ApiModelProperty(value = "退货类型")
    private String name;

    private Integer sort;

    @ApiModelProperty(value = "状态：0->不启用；1->启用")
    private Integer status;

    @ApiModelProperty(value = "添加时间")
    private Date createTime;

    private static final long serialVersionUID = 1L;


}
