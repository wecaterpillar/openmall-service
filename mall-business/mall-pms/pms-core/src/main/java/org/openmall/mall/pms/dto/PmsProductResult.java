package org.openmall.mall.pms.dto;

/**
 * 查询单个产品进行修改时返回的结果
 */
@lombok.Data
public class PmsProductResult extends PmsProductParam {
    //商品所选分类的父id
    private Long cateParentId;

}
