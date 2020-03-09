package org.openmall.mall.sms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * 限时购
 *
 * 用于存储限时购活动的信息，包括开始时间、结束时间以及上下线状态。
 */
@lombok.Data
public class SmsFlashPromotion implements Serializable {
    private Long id;

    private String title;

    @ApiModelProperty(value = "开始日期")
    private Date startDate;

    @ApiModelProperty(value = "结束日期")
    private Date endDate;

    @ApiModelProperty(value = "上下线状态")
    private Integer status;

    @ApiModelProperty(value = "秒杀时间段名称")
    private Date createTime;

    private static final long serialVersionUID = 1L;


}
