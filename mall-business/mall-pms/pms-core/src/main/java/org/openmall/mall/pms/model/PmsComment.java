package org.openmall.mall.pms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * 商品评论
 */
@lombok.Data
public class PmsComment implements Serializable {
    private Long id;

    @ApiModelProperty(value = "商品id")
    private Long productId;

    private String memberNickName;

    private String productName;

    @ApiModelProperty(value = "评价星数：0->5")
    private Integer star;

    @ApiModelProperty(value = "评价的ip")
    private String memberIp;

    private Date createTime;

    private Integer showStatus;

    @ApiModelProperty(value = "购买时的商品属性")
    private String productAttribute;

    private Integer collectCouont;

    @ApiModelProperty(value = "评论用户头像")
    private String memberIcon;



    private String content;

    @ApiModelProperty(value = "上传图片地址，以逗号隔开")
    private String pics;



    private Integer readCount;

    private Integer replayCount;


    private static final long serialVersionUID = 1L;


}
