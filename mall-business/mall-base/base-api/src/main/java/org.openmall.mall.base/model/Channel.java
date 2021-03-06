package org.openmall.mall.base.model;

/**
 * 业务渠道定义
 */
@lombok.Data
public class Channel {

    private Long id;  //渠道编号
    private Long merchantId;  //所属商户Id
    private Long type;  //渠道类型
    private String name;
    private boolean enable;

    //国家
    private String countryCode;
    private String countryName;
    //语言
    private String language;   //当前语言/默认语言
    private String languages;  //可使用语言，逗号分割
    //货币
    private String curCode;
    private String curName;
    private String curCodes;  //可使用货币，逗号分割

}
