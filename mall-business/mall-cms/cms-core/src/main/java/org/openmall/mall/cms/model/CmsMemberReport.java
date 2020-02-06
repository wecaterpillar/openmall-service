package org.openmall.mall.cms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * 会员举报
 */
@lombok.Data
public class CmsMemberReport implements Serializable {
    private Long id;

    @ApiModelProperty(value = "举报类型：0->商品评价；1->话题内容；2->用户评论")
    private Integer reportType;

    @ApiModelProperty(value = "举报人")
    private String reportMemberName;

    private Date createTime;

    private String reportObject;

    @ApiModelProperty(value = "举报状态：0->未处理；1->已处理")
    private Integer reportStatus;

    @ApiModelProperty(value = "处理结果：0->无效；1->有效；2->恶意")
    private Integer handleStatus;

    private String note;

    private static final long serialVersionUID = 1L;

}
