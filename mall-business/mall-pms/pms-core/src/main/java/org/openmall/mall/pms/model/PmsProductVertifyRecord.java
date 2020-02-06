package org.openmall.mall.pms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * 商品审核记录
 *
 * 用于记录商品审核记录
 */
@lombok.Data
public class PmsProductVertifyRecord implements Serializable {
    private Long id;

    private Long productId;

    private Date createTime;

    @ApiModelProperty(value = "审核人")
    private String vertifyMan;

    private Integer status;

    @ApiModelProperty(value = "反馈详情")
    private String detail;

    private static final long serialVersionUID = 1L;


}
