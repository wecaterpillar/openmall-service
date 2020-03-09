package org.openmall.mall.sms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * 人气推荐商品
 *
 * 用于管理首页显示的人气推荐信息。
 *
 */
@lombok.Data
public class SmsHomeRecommendProduct implements Serializable {
    private Long id;

    private Long productId;

    private String productName;

    private Integer recommendStatus;

    private Integer sort;

    private static final long serialVersionUID = 1L;

}
