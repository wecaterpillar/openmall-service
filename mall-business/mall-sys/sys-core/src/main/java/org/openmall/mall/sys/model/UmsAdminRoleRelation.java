package org.openmall.mall.sys.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@lombok.Data
public class UmsAdminRoleRelation implements Serializable {
    private Long id;

    private Long adminId;

    private Long roleId;

    private static final long serialVersionUID = 1L;

}
