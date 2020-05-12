package com.online.edu.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xujin
 * @createtime 2020-05-02 14:27
 * @description
 */
@Data
@ApiModel(value = "Course查询对象", description = "课程查询对象封装")
public class CourseQueryReq implements Serializable {
    private static final long serialVersionUID = -3912511975610240247L;
    @ApiModelProperty(value = "课程名称")
    private String title;

    @ApiModelProperty(value = "讲师id")
    private String teacherId;

    @ApiModelProperty(value = "二级类别id")
    private String subjectId;
}
