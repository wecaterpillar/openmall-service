package org.openmall.mall.cms.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 帮助
 */
@Data
public class CmsHelp implements Serializable {
    private Long id;

    private Long categoryId;

    private String icon;

    private String title;

    private Integer showStatus;

    private Date createTime;

    private Integer readCount;

    private String content;

    private static final long serialVersionUID = 1L;


}
