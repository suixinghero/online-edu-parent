package com.online.edu.req;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * @author xujin
 * @createtime 2020-05-02 18:30
 * @description
 */
@Data
@ApiModel(value="ChapterAddReq", description="课程章节添加对象")
public class ChapterAddReq implements Serializable {
    private static final long serialVersionUID = -6151681616740424423L;
    @ApiModelProperty(value = "课程ID")
    private String courseId;

    @ApiModelProperty(value = "章节名称")
    private String title;

    @ApiModelProperty(value = "显示排序")
    private Integer sort;

}
