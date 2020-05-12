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
@ApiModel(value="ChapterUpdateReq", description="课程章节更新对象")
public class ChapterUpdateReq implements Serializable {

    private static final long serialVersionUID = -5956548690768171403L;
    @ApiModelProperty(value = "章节ID")
    private String id;

    @ApiModelProperty(value = "章节名称")
    private String title;

    @ApiModelProperty(value = "显示排序")
    private Integer sort;

}
