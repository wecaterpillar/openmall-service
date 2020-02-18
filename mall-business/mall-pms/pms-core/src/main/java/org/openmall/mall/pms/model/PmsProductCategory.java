package org.openmall.mall.pms.model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * 商品分类
 */
@lombok.Data
public class PmsProductCategory implements Serializable {

    @Id
    private Long id;

    @ApiModelProperty(value = "上级分类的编号：0表示一级分类")
    private Long parentId;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "分类级别：0->1级；1->2级")
    private Integer level;

    @ApiModelProperty(value = "商品数量")
    private Integer productCount;

    @ApiModelProperty(value = "商品单位")
    private String productUnit;

    @ApiModelProperty(value = "是否显示在导航栏：0->不显示；1->显示")
    private Integer navStatus;

    @ApiModelProperty(value = "显示状态：0->不显示；1->显示")
    private Integer showStatus;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "关键字")
    private String keywords;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "父树分类")
    private String parentTreeId;

    @ApiModelProperty(value = "树分类,格式 [parentTreeId:id] ")
    public String getTreeId(){
        if(parentId==null || parentId==0){
            return this.getId()+":";
        }
        return parentTreeId+this.getId()+":";
    }

    private static final long serialVersionUID = 1L;

}
