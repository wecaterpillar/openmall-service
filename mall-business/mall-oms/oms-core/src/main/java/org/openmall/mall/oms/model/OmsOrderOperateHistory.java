package org.openmall.mall.oms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * 订单操作历史
 */
@lombok.Data
public class OmsOrderOperateHistory implements Serializable {
    private Long id;

    @ApiModelProperty(value = "订单id")
    private Long orderId;

    @ApiModelProperty(value = "操作人：用户；系统；后台管理员")
    private String operateMan;

    @ApiModelProperty(value = "操作时间")
    private Date createTime;

    @ApiModelProperty(value = "订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单")
    private Integer orderStatus;

    @ApiModelProperty(value = "备注")
    private String note;

    private static final long serialVersionUID = 1L;


}
