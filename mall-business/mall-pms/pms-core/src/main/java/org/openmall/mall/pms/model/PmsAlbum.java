package org.openmall.mall.pms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * 商品相册
 */
@lombok.Data
public class PmsAlbum implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "封面")
    private String coverPic;

    private Integer picCount;

    private Integer sort;

    private String description;

}
