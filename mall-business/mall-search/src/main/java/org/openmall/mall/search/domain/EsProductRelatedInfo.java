package org.openmall.mall.search.domain;

import java.util.List;

/**
 * 搜索相关商品品牌名称，分类名称及属性
 *
 * TODO 价格区间待增加
 */
@lombok.Data
public class EsProductRelatedInfo {

    private List<String> brandNames;
    private List<String> productCategoryNames;
    private List<ProductAttr>   productAttrs;

    private Long totalNum;

    @lombok.Data
    public static class ProductAttr{
        private Long attrId;
        private String attrName;
        private List<String> attrValues;

    }
}
