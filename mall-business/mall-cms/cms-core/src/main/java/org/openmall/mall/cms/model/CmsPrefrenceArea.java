package org.openmall.mall.cms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * 优选专区
 */
@lombok.Data
public class CmsPrefrenceArea implements Serializable {
    private Long id;

    private String name;

    private String subTitle;

    private Integer sort;

    private Integer showStatus;

    @ApiModelProperty(value = "展示图片")
    private byte[] pic;

    private static final long serialVersionUID = 1L;


}
