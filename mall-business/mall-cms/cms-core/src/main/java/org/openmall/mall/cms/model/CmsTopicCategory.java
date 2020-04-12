package org.openmall.mall.cms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * 话题分类
 */
@lombok.Data
public class CmsTopicCategory implements Serializable {
    private Long id;

    @ApiModelProperty(value = "分类名称")
    private String name;

    @ApiModelProperty(value = "分类图标")
    private String icon;

    @ApiModelProperty(value = "数量")
    private Integer subjectCount;

    @ApiModelProperty(value = "显示状态")
    private Integer showStatus;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    private static final long serialVersionUID = 1L;


}
