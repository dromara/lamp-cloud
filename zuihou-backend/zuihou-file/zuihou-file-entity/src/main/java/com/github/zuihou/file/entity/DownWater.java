package com.github.zuihou.file.entity;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zuihou.base.entity.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

/**
 * <p>
 * 实体类
 * 文件下载流水
 * </p>
 *
 * @author zuihou
 * @since 2019-06-24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("f_down_water")
@ApiModel(value = "DownWater", description = "文件下载流水")
public class DownWater extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 应用CODE
     * #c_application
     */
    @ApiModelProperty(value = "应用CODE")
    @Length(max = 64, message = "应用CODE长度不能超过64")
    @TableField("app_code")
    private String appCode;

    /**
     * 文件ID
     * #f_file
     */
    @ApiModelProperty(value = "文件ID")
    @NotNull(message = "文件ID不能为空")
    @TableField("file_id")
    private Long fileId;

    /**
     * 创建年月
     * 格式：yyyy-MM 用于统计
     */
    @ApiModelProperty(value = "创建年月")
    @Length(max = 10, message = "创建年月长度不能超过10")
    @TableField("create_month")
    private String createMonth;

    /**
     * 创建年周
     * yyyy-ww 用于统计
     */
    @ApiModelProperty(value = "创建年周")
    @Length(max = 10, message = "创建年周长度不能超过10")
    @TableField("create_week")
    private String createWeek;

    /**
     * 创建年月日
     * 格式： yyyy-MM-dd 用于统计
     */
    @ApiModelProperty(value = "创建年月日")
    @Length(max = 12, message = "创建年月日长度不能超过12")
    @TableField("create_day")
    private String createDay;


    @Builder
    public DownWater(Long id, Long createUser, LocalDateTime createTime, LocalDateTime updateTime, Long updateUser,
                     String appCode, Long fileId, String createMonth, String createWeek, String createDay) {
        this.id = id;
        this.createUser = createUser;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.updateUser = updateUser;
        this.appCode = appCode;
        this.fileId = fileId;
        this.createMonth = createMonth;
        this.createWeek = createWeek;
        this.createDay = createDay;
    }

}
