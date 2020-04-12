package org.openmall.mall.portal.home.domain;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 楼层内容定义
 */
@lombok.Data
public class HomeLayerContent {


    private Integer index; //顺序号(内部保留信息）
    private String name; //名称（内部保留信息)
    @ApiModelProperty(value = "楼层类型，1-专题分类，2-话题分类，3-帮助分类，4-广告位，10-秒杀活动，11-新品推荐，12-人气推荐，13-品牌推荐，14-专题推荐，15-优选专区")
    private Integer type; //楼层类型
    private Integer count; //数据数量 0-表示不限制，大于0表示返回数据的数量
    private String refIds; //关联数据编号，type=1/2/3 关联专题分类ID type=4 关联多个广告位编号
    private List<Object> data; //关联数据

    public HomeLayerContent(){

    }

    public HomeLayerContent(int index, String name, int type, int count, String refIds){
        this.index = index;
        this.name = name;
        this.type = type;
        this.count = count;
        this.refIds = refIds;
    }

}
