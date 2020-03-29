package org.openmall.mall.base.model;

/**
 * 业务渠道定义
 */
@lombok.Data
public class Channel {

    private Long id;  //渠道编号
    private Long ChannelType;  //渠道类型
    private Long merchantCode;  //所属商户

    private String language;   //当前语言/默认语言
    private String languages;  //可使用语言，逗号分割

}
