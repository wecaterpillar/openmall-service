package org.openmall.mall.ums.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * 会员关注产品类型
 */
@lombok.Data
public class UmsMemberProductCategoryRelation implements Serializable {
    private Long id;

    private Long memberId;

    private Long productCategoryId;

    private static final long serialVersionUID = 1L;

}
