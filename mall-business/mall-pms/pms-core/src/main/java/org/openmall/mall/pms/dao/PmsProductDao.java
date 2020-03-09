package org.openmall.mall.pms.dao;

import org.openmall.mall.pms.dto.PmsProductResult;
import org.apache.ibatis.annotations.Param;


/**
 * 商品自定义Dao
 */
public interface PmsProductDao {
    /**
     * 获取商品编辑信息
     */
    PmsProductResult getUpdateInfo(@Param("id") Long id);
}
