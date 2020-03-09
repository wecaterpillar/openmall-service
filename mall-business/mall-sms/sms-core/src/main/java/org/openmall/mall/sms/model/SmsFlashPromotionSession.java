package org.openmall.mall.sms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * 限时购场次
 *
 * 用于存储限时购场次的信息，在一天中，一个限时购活动会有多个不同的活动时间段。
 */
@lombok.Data
public class SmsFlashPromotionSession implements Serializable {
    @ApiModelProperty(value = "编号")
    private Long id;

    @ApiModelProperty(value = "场次名称")
    private String name;

    @ApiModelProperty(value = "每日开始时间")
    private Date startTime;

    @ApiModelProperty(value = "每日结束时间")
    private Date endTime;

    @ApiModelProperty(value = "启用状态：0->不启用；1->启用")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    private static final long serialVersionUID = 1L;


}
