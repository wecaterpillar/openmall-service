package org.openmall.mall.pms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *  商品SKU
 *
 *  SKU(Stock Keeping Unit)是指库存量单位，SPU(Standard Product Unit)是指标准产品单位。
 *  举个例子：iphone xs是一个SPU，而iphone xs 公开版 64G 银色是一个SKU。
 */
@lombok.Data
public class PmsSkuStock implements Serializable {
    private Long id;

    @ApiModelProperty(value = "商品id")
    private Long productId;

    @ApiModelProperty(value = "sku编码")
    private String skuCode;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "库存")
    private Integer stock;

    @ApiModelProperty(value = "预警库存")
    private Integer lowStock;

    @ApiModelProperty(value = "规格属性1")
    private String sp1;

    @ApiModelProperty(value = "规格属性2")
    private String sp2;

    @ApiModelProperty(value = "规格属性3")
    private String sp3;

    @ApiModelProperty(value = "展示图片")
    private String pic;

    @ApiModelProperty(value = "销量")
    private Integer sale;

    @ApiModelProperty(value = "单品促销价格")
    private BigDecimal promotionPrice;

    @ApiModelProperty(value = "锁定库存")
    private Integer lockStock;

    private static final long serialVersionUID = 1L;


}
