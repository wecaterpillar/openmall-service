package org.openmall.mall.cms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * 专题评论
 */
@lombok.Data
public class CmsSubjectComment implements Serializable {
    private Long id;

    private Long subjectId;

    private String memberNickName;

    private String memberIcon;

    private String content;

    private Date createTime;

    private Integer showStatus;

    private static final long serialVersionUID = 1L;


}
