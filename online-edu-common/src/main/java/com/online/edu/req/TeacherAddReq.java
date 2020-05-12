package com.online.edu.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 讲师添加请求封装对象
 * </p>
 *
 * @author xujin
 * @since 2020-04-02
 */
@Data
@ApiModel(value="Teacher添加对象", description="讲师添加对象封装")
public class TeacherAddReq implements Serializable {
    private static final long serialVersionUID = 7878169874283571757L;
    @ApiModelProperty(value = "讲师姓名")
    private String name;

    @ApiModelProperty(value = "讲师资历,一句话说明讲师")
    private String intro;

    @ApiModelProperty(value = "讲师简介")
    private String career;

    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;

    @ApiModelProperty(value = "讲师头像")
    private String avatar;

    @ApiModelProperty(value = "排序")
    private Integer sort;

}
