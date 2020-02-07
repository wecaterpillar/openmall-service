package org.openmall.mall.sys.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@lombok.Data
public class UmsRolePermissionRelation implements Serializable {
    private Long id;

    private Long roleId;

    private Long permissionId;

    private static final long serialVersionUID = 1L;

}
