package org.openmall.mall.pms.domain;

import org.openmall.mall.pms.model.PmsProduct;
import org.openmall.mall.pms.model.PmsProductAttribute;
import org.openmall.mall.pms.model.PmsSkuStock;

import java.util.List;

/**
 * 购物车中选择规格的商品信息
 */
@lombok.Data
public class CartProduct extends PmsProduct {

    private List<PmsProductAttribute> productAttributeList;
    private List<PmsSkuStock> skuStockList;

}
