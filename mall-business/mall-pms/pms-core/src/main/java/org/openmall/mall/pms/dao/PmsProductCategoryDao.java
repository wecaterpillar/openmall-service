package org.openmall.mall.pms.dao;

import org.openmall.mall.pms.dto.PmsProductCategoryWithChildrenItem;

import java.util.List;

/**
 * 商品分类自定义Dao
 */
public interface PmsProductCategoryDao {
    List<PmsProductCategoryWithChildrenItem> listWithChildren();
    List<PmsProductCategoryWithChildrenItem>  navListWithChildren();
}
