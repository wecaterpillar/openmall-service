package org.openmall.mall.pms.dao;

import org.openmall.mall.pms.dto.ProductAttrInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义商品属性Dao
 */
public interface PmsProductAttributeDao {
    List<ProductAttrInfo> getProductAttrInfo(@Param("id") Long productCategoryId);
}