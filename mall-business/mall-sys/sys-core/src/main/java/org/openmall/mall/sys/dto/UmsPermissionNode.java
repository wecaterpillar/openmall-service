package org.openmall.mall.sys.dto;

import org.openmall.mall.sys.model.UmsPermission;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 */
public class UmsPermissionNode extends UmsPermission {
    @Getter
    @Setter
    private List<UmsPermissionNode> children;
}
