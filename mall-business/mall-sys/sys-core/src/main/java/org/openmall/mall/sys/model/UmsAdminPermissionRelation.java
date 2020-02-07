package org.openmall.mall.sys.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@lombok.Data
public class UmsAdminPermissionRelation implements Serializable {
    private Long id;

    private Long adminId;

    private Long permissionId;

    private Integer type;

    private static final long serialVersionUID = 1L;

}
