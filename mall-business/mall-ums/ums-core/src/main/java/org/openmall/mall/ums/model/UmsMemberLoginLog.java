package org.openmall.mall.ums.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * 会员登录记录
 */
@lombok.Data
public class UmsMemberLoginLog implements Serializable {
    private Long id;

    private Long memberId;

    private Date createTime;

    private String ip;

    private String city;

    @ApiModelProperty(value = "登录类型：0->PC；1->android;2->ios;3->小程序")
    private Integer loginType;

    private String province;

    private static final long serialVersionUID = 1L;


}
