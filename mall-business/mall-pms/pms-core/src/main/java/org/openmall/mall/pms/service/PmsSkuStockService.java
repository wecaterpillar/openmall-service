package org.openmall.mall.pms.service;

import org.openmall.mall.pms.model.PmsSkuStock;

import java.util.List;

/**
 * sku商品库存管理Service
 */
public interface PmsSkuStockService {
    /**
     * 根据产品id和skuCode模糊搜索
     */
    List<PmsSkuStock> getList(Long pid, String keyword);

    /**
     * 批量更新商品库存信息
     */
    int update(Long pid, List<PmsSkuStock> skuStockList);
}
