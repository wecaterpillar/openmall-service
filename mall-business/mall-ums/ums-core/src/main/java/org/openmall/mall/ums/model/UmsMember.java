package org.openmall.mall.ums.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * 会员信息
 */
@lombok.Data
public class UmsMember implements Serializable {

    private Long id;

    @ApiModelProperty(value = "会员级别id")
    private Long memberLevelId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    //TODO 待增加邮箱字段，暂时使用phone字段来存放手机号码和邮箱地址
    //@ApiModelProperty(value = "电子邮件")
    //private String email;

    @ApiModelProperty(value = "帐号启用状态:0->禁用；1->启用")
    private Integer status;

    @ApiModelProperty(value = "注册时间")
    private Date createTime;

    @ApiModelProperty(value = "头像")
    private String icon;

    @ApiModelProperty(value = "性别：0->未知；1->男；2->女")
    private Integer gender;

    @ApiModelProperty(value = "生日")
    private Date birthday;

    @ApiModelProperty(value = "所做城市")
    private String city;

    @ApiModelProperty(value = "职业")
    private String job;

    @ApiModelProperty(value = "个性签名")
    private String personalizedSignature;

    @ApiModelProperty(value = "用户来源")
    private Integer sourceType;

    @ApiModelProperty(value = "积分")
    private Integer integration;

    @ApiModelProperty(value = "成长值")
    private Integer growth;

    @ApiModelProperty(value = "剩余抽奖次数")
    private Integer luckeyCount;

    @ApiModelProperty(value = "历史积分数量")
    private Integer historyIntegration;

    private static final long serialVersionUID = 1L;


}
