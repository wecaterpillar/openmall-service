package org.openmall.mall.sys.dto;

import org.openmall.mall.sys.model.UmsMenu;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 后台菜单节点封装
 */
@Getter
@Setter
public class UmsMenuNode extends UmsMenu {
    private List<UmsMenuNode> children;
}
