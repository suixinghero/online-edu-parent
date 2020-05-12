package com.online.edu.req;

/**
 * @author xujin
 * @createtime 2020-05-03 14:56
 * @description
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "VideoInfoFormAddReq", description = "编辑课时基本信息的表单添加对象")
@Data
public class VideoInfoFormAddReq implements Serializable {

    private static final long serialVersionUID = 3240497312846460874L;
    @ApiModelProperty(value = "小节名称")
    private String title;

    @ApiModelProperty(value = "课程ID")
    private String courseId;

    @ApiModelProperty(value = "章节ID")
    private String chapterId;

    @ApiModelProperty(value = "视频资源")
    private String videoSourceId;

    @ApiModelProperty(value = "显示排序")
    private Integer sort;

    @ApiModelProperty(value = "是否可以试听：0默认 1免费")
    private Boolean free;

    @ApiModelProperty(value = "云服务器上存储的视频文件名称")
    private String videoOriginalName;
}