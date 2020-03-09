package org.openmall.mall.pms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * 商品相册照片
 */
@lombok.Data
public class PmsAlbumPic implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "相册id")
    private Long albumId;

    private String pic;


}
