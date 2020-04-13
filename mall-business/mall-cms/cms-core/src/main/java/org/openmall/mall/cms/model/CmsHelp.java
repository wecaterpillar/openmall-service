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

    @ApiModelProperty(value = "所属分类")
    private Long categoryId;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "显示状态")
    private Integer showStatus;

    private Date createTime;

    @ApiModelProperty(value = "阅读统计")
    private Integer readCount;

    @ApiModelProperty(value = "内容")
    private String content;

    private static final long serialVersionUID = 1L;


}
