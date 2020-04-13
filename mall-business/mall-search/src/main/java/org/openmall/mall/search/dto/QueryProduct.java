package org.openmall.mall.search.dto;

import com.google.common.collect.Lists;
import org.openmall.mall.search.domain.EsProductRelatedInfo;

import java.math.BigDecimal;
import java.util.List;

@lombok.Data
public class QueryProduct {

    private Long id;
    /**
     * 关键词
     */
    private String keyword;
    /**
     * 价格区间
     */
    private BigDecimal minPrice;
    private BigDecimal maxPrice;

    private Long brandId;
    private Long productCategoryId;

    /*
     * @Name: 品牌名称
     * @Example: ["小米", "华为"]
     * @Description:
     */
    private List<String> brands = Lists.newArrayList();

    /*
     * @Name: 商品名称
     * @Example: 小米9pro 5G版手机 钛银黑 12G+512G
     * @Description:
     */
    private String name;

    /*
     * @Name: 规格参数
     * @Example: [
        {
            "attrName": "颜色",
            "attrValues": ["黑色"]
        },
        {
            "attrName": "内存",
            "attrValues": ["64G", "128G"]
        }
     ]
     * @Description:
     */
    private List<EsProductRelatedInfo.ProductAttr> productAttrs = Lists.newArrayList();

    /**
     * 排序字段:0->按相关度；1->按新品；2->按销量；3->价格从低到高；4->价格从高到低
     */
    private Integer sort;
}
