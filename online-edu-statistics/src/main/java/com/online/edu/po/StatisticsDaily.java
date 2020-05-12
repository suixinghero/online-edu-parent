package com.online.edu.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 网站统计日数据
 * </p>
 *
 * @author xujin
 * @since 2020-05-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="StatisticsDaily对象", description="网站统计日数据")
public class StatisticsDaily implements Serializable {

    private static final long serialVersionUID = 3969803349208944203L;
    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "统计日期")
    private String dateCalculated;

    @ApiModelProperty(value = "注册人数")
    private Integer registerNum;

    @ApiModelProperty(value = "登录人数")
    private Integer loginNum;

    @ApiModelProperty(value = "每日播放视频数")
    private Integer videoViewNum;

    @ApiModelProperty(value = "每日新增课程数")
    private Integer courseNum;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
    public StatisticsDaily(){}
    private StatisticsDaily(Builder builder){
       this.setId(builder.id);
       this.setDateCalculated(builder.dateCalculated);
       this.setRegisterNum(builder.registerNum);
       this.setLoginNum(builder.loginNum);
       this.setVideoViewNum(builder.videoViewNum);
       this.setCourseNum(builder.courseNum);
       this.setGmtCreate(builder.gmtCreate);
       this.setGmtModified(builder.gmtModified);

    }
    public static Builder newBuilder() {
        return new Builder();
    }
    public static final class Builder{
        private String id;
        private String dateCalculated;
        private Integer registerNum;
        private Integer loginNum;
        private Integer videoViewNum;
        private Integer courseNum;
        private Date gmtCreate;
        private Date gmtModified;
        private Builder(){}
        public Builder id(String id){
            this.id = id;
            return this;
        }
        public Builder dateCalculated(String dateCalculated){
            this.dateCalculated = dateCalculated;
            return this;
        }
        public Builder registerNum(Integer registerNum){
            this.registerNum = registerNum;
            return this;
        }
        public Builder loginNum(Integer loginNum){
            this.loginNum = loginNum;
            return this;
        }
        public Builder videoViewNum(Integer videoViewNum){
            this.videoViewNum = videoViewNum;
            return this;
        }
        public Builder courseNum(Integer courseNum){
            this.courseNum = courseNum;
            return this;
        }
        public Builder gmtCreate(Date gmtCreate){
            this.gmtCreate = gmtCreate;
            return this;
        }
        public Builder gmtModified(Date gmtModified){
            this.gmtModified = gmtModified;
            return this;
        }
        public StatisticsDaily build() {
            return new StatisticsDaily(this);
        }
    }

}
