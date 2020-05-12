package com.online.edu.req;

/**
 * @author xujin
 * @package-name com.online.edu.req
 * @createtime 2020-04-04 18:50
 * @description 讲师查询对象封装
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "Teacher查询对象", description = "讲师查询对象封装")
public class TeacherQueryReq implements Serializable {
    private static final long serialVersionUID = -1461901733992143565L;
    @ApiModelProperty(value = "教师名称,模糊查询")
    private String name;
    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;
    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private String begin;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换
    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String end;
}
