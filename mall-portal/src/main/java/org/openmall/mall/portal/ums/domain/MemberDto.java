package org.openmall.mall.portal.ums.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by qingsong on 2020-02-12
 */
@Data
public class MemberDto {
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "会员级别id")
    private Long memberLevelId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "头像")
    private String icon;
}
