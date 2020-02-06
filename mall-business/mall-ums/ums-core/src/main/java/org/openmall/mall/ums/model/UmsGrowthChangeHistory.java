package org.openmall.mall.ums.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * 会员积分更改记录-成长Growth
 */
@lombok.Data
public class UmsGrowthChangeHistory implements Serializable {
    private Long id;

    private Long memberId;

    private Date createTime;

    @ApiModelProperty(value = "改变类型：0->增加；1->减少")
    private Integer changeType;

    @ApiModelProperty(value = "积分改变数量")
    private Integer changeCount;

    @ApiModelProperty(value = "操作人员")
    private String operateMan;

    @ApiModelProperty(value = "操作备注")
    private String operateNote;

    @ApiModelProperty(value = "积分来源：0->购物；1->管理员修改")
    private Integer sourceType;

    private static final long serialVersionUID = 1L;


}
