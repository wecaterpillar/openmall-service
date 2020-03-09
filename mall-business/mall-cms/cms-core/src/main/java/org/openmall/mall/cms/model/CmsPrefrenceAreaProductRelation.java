package org.openmall.mall.cms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * 优选专区和产品关联
 */
@lombok.Data
public class CmsPrefrenceAreaProductRelation implements Serializable {
    private Long id;

    private Long prefrenceAreaId;

    private Long productId;

    private static final long serialVersionUID = 1L;

}
