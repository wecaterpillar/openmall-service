package org.openmall.mall.search.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 搜索中的商品信息
 */
@lombok.Data
@lombok.NoArgsConstructor
@Document(indexName = "pms", type = "product",shards = 1,replicas = 0)
public class EsProduct implements Serializable {

    private static final long serialVersionUID = -1L;
    @Id
    private Long id;

    @Field(type = FieldType.Keyword)
    private String productSn;

    private Long brandId;

    @Field(type = FieldType.Keyword)
    private String brandName;

    @Field(type = FieldType.Long)
    private Long productCategoryId;

    @Field(type = FieldType.Keyword)
    private String productCategoryName;

    private String pic;

    @Field(analyzer = "ik_max_word",type = FieldType.Text, fielddata = true)
    private String name;

    @Field(analyzer = "ik_max_word",type = FieldType.Text, fielddata = true)
    private String subTitle;

    @Field(analyzer = "ik_max_word",type = FieldType.Text, fielddata = true)
    private String keywords;

    @Field(type = FieldType.Double)
    private BigDecimal price;

    private Integer sale;
    private Integer newStatus;
    private Integer recommandStatus;
    private Integer stock;
    private Integer promotionType;
    private Integer sort;

    @Field(type = FieldType.Nested)
    private List<EsProductAttributeValue> attrValueList;


    public interface FieldName {
        String ID = "id";
        String NAME = "name.keyword";
        String SUBTITLE = "subTitle.keyword";
        String KEYWORDS = "keywords.keyword";
        String CATEGORY_ID = "productCategoryId";
        String CATEGORY = "productCategoryName.keyword";
        String BRAND = "brandName.keyword";
        String PRICE = "price";
        String ATTRS = "attrValueList";
        String ATTRS_NAME = "attrValueList.name";
        String ATTRS_VALUE = "attrValueList.value";
    }
}
