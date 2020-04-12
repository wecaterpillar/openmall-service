package org.openmall.mall.cms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * 帮助分类
 */
@lombok.Data
public class CmsHelpCategory implements Serializable {

    private Long id;

    @ApiModelProperty(value = "分类名称")
    private String name;

    @ApiModelProperty(value = "分类图标")
    private String icon;

    @ApiModelProperty(value = "帮助数量")
    private Integer helpCount;

    @ApiModelProperty(value = "显示状态")
    private Integer showStatus;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    private static final long serialVersionUID = 1L;


}
