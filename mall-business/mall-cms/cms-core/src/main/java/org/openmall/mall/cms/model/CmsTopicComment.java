package org.openmall.mall.cms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * 话题评论
 */
@lombok.Data
public class CmsTopicComment implements Serializable {
    private Long id;

    @ApiModelProperty(value = "会员昵称")
    private String memberNickName;

    @ApiModelProperty(value = "话题ID")
    private Long topicId;

    @ApiModelProperty(value = "会员头像")
    private String memberIcon;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "显示状态")
    private Integer showStatus;

    private static final long serialVersionUID = 1L;


}
