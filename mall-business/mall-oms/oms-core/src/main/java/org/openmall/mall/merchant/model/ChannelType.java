package org.openmall.mall.merchant.model;

public class  ChannelType {

    public static final ChannelType MALL = new ChannelType(1L,"MALL","");    //商城
    public static final ChannelType ESHOP = new ChannelType(2L,"ESHOP","");  //在线商店
    public static final ChannelType STOTE = new ChannelType(3L,"STORE","");   //线下门店
    public static final ChannelType SOCIAL = new ChannelType(10L,"SOCIAL","");  //社交渠道
    public static final ChannelType THIRD = new ChannelType(20L,"THIRD","");   //第三方电商

    private Long id;
    private String name;
    private String desc;

    public ChannelType(Long id, String name, String desc){
        this.id = id;
        this.name = name;
        this.desc = desc;
    }
}
