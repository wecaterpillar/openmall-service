package org.openmall.mall.sms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * 首页轮播广告表
 *
 * 用于管理首页显示的轮播广告信息。
 */
@lombok.Data
public class SmsHomeAdvertise implements Serializable {
    private Long id;

    private String name;

    @ApiModelProperty(value = "轮播位置：0->PC首页轮播；1->app首页轮播, 11-20 为广告位编号")
    private Integer type;

    private String pic;

    private Date startTime;

    private Date endTime;

    @ApiModelProperty(value = "上下线状态：0->下线；1->上线")
    private Integer status;

    @ApiModelProperty(value = "点击数")
    private Integer clickCount;

    @ApiModelProperty(value = "下单数")
    private Integer orderCount;

    @ApiModelProperty(value = "链接地址")
    private String url;

    @ApiModelProperty(value = "备注")
    private String note;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    private static final long serialVersionUID = 1L;


}
