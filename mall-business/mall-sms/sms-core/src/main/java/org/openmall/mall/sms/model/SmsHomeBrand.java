package org.openmall.mall.sms.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 首页品牌推荐
 *
 * 用于管理首页显示的品牌制造商直供信息。
 */
@lombok.Data
public class SmsHomeBrand implements Serializable {
    private Long id;

    private Long brandId;

    private String brandName;

    private Integer recommendStatus;

    private Integer sort;

    private static final long serialVersionUID = 1L;


}
