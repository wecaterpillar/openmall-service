package org.openmall.mall.ums.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * 会员任务
 */
@lombok.Data
public class UmsMemberTask implements Serializable {
    private Long id;

    private String name;

    @ApiModelProperty(value = "赠送成长值")
    private Integer growth;

    @ApiModelProperty(value = "赠送积分")
    private Integer intergration;

    @ApiModelProperty(value = "任务类型：0->新手任务；1->日常任务")
    private Integer type;

    private static final long serialVersionUID = 1L;

}
