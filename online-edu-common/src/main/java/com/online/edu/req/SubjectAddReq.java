package com.online.edu.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 课程科目添加请求
 * </p>
 *
 * @author xujin
 * @since 2020-04-29
 */
@Data
@ApiModel(value="Subject添加对象", description="课程科目添加请求")
public class SubjectAddReq implements Serializable {

    private static final long serialVersionUID = 8661525243197068688L;
    @ApiModelProperty(value = "类别名称")
    private String title;
    @ApiModelProperty(value = "父ID")
    private String parentId;

}
