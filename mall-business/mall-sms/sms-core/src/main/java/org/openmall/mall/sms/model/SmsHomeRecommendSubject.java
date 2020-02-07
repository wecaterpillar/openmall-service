package org.openmall.mall.sms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * 首页专题推荐
 *
 * 用于管理首页显示的专题精选信息。
 */
@lombok.Data
public class SmsHomeRecommendSubject implements Serializable {
    private Long id;

    private Long subjectId;

    private String subjectName;

    private Integer recommendStatus;

    private Integer sort;

    private static final long serialVersionUID = 1L;
}
