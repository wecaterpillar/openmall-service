package org.openmall.mall.base.model;

/**
 * 商户
 *
 */
@lombok.Data
public class Merchant {

    public static Merchant defaultMerchant = new Merchant(1L, "1000","mall", true, true, false);

    public static Merchant getDefaultMerchant() {
        return defaultMerchant;
    }

    private Long id;
    private String code;
    private String name;
    private String description;

    private Boolean isMallOp = true;      //是否商城直营
    private Boolean hasChannel = true;    //是否开通销售渠道
    private Boolean hasSupply = false;    //是否开通供应商

    public Merchant(Long id, String code,String name, Boolean isMallOp, Boolean hasChannel, Boolean hasSupply) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.isMallOp = isMallOp;
        this.hasChannel = hasChannel;
        this.hasSupply = hasSupply;
    }
}
