package org.openmall.mall.sms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * 限时购通知记录
 *
 * 用于存储会员的限时购预约记录，当有的限时购场次还未开始时，会员可以进行预约操作，当场次开始时，系统会进行提醒。
 */
@lombok.Data
public class SmsFlashPromotionLog implements Serializable {
    private Integer id;

    private Integer memberId;

    private Long productId;

    private String memberPhone;

    private String productName;

    @ApiModelProperty(value = "会员订阅时间")
    private Date subscribeTime;

    private Date sendTime;

    private static final long serialVersionUID = 1L;


}
