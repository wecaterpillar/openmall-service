package org.openmall.mall.merchant.model;

/**
 * 渠道定义
 */
public class Channel {

    private Long id;  //渠道编号
    private Long ChannelTypeId;  //渠道类型
    private Long merchantId;  //所属商户

    private String language;   //当前语言/默认语言
    private String languages;  //可使用语言，逗号分割

}
