package org.openmall.mall.merchant.model;

/**
 * 商户
 */
public class Merchant {

    private Long id;
    private String name;

    private Boolean isMallOp = true;      //是否商城直营
    private Boolean hasChannel = true;    //是否开通销售渠道
    private Boolean hasSupply = false;    //是否开通供应商

}
