package org.openmall.mall.cms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * 专题分类
 */
@lombok.Data
public class CmsSubjectCategory implements Serializable {
    private Long id;

    private String name;

    @ApiModelProperty(value = "分类图标")
    private String icon;

    @ApiModelProperty(value = "专题数量")
    private Integer subjectCount;

    private Integer showStatus;

    private Integer sort;

    private static final long serialVersionUID = 1L;

}
