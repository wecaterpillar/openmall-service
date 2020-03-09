package org.openmall.mall.cms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * 话题
 */
@lombok.Data
public class CmsTopic implements Serializable {
    private Long id;

    private Long categoryId;

    private String name;

    private Date createTime;

    private Date startTime;

    private Date endTime;

    @ApiModelProperty(value = "参与人数")
    private Integer attendCount;

    @ApiModelProperty(value = "关注人数")
    private Integer attentionCount;

    private Integer readCount;

    @ApiModelProperty(value = "奖品名称")
    private String awardName;

    @ApiModelProperty(value = "参与方式")
    private String attendType;

    @ApiModelProperty(value = "话题内容")
    private String content;

    private static final long serialVersionUID = 1L;


}
