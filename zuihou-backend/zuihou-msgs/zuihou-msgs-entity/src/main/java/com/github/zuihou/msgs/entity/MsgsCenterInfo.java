package com.github.zuihou.msgs.entity;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zuihou.base.entity.Entity;
import com.github.zuihou.msgs.enumeration.MsgsCenterType;

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
 * 消息中心全量表
 * </p>
 *
 * @author zuihou
 * @since 2019-08-01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("msgs_center_info")
@ApiModel(value = "MsgsCenterInfo", description = "消息中心全量表")
public class MsgsCenterInfo extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 业务ID
     * 业务表的唯一id
     */
    @ApiModelProperty(value = "业务ID")
    @Length(max = 64, message = "业务ID长度不能超过64")
    @TableField("biz_id")
    private String bizId;

    /**
     * 业务类型
     * #MsgsBizType.code
     */
    @ApiModelProperty(value = "业务类型")
    @NotEmpty(message = "业务类型不能为空")
    @Length(max = 64, message = "业务类型长度不能超过64")
    @TableField("biz_type")
    private String bizType;

    /**
     * 消息类型
     * #MsgsCenterType{WAIT:待办;NOTIFY:通知;PUBLICITY:公示公告;WARN:预警;}
     */
    @ApiModelProperty(value = "消息类型")
    @NotNull(message = "消息类型不能为空")
    @TableField("msgs_center_type")
    private MsgsCenterType msgsCenterType;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    @Length(max = 100, message = "标题长度不能超过100")
    @TableField("title")
    private String title;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    @Length(max = 500, message = "内容长度不能超过500")
    @TableField("content")
    private String content;

    /**
     * 作者名称
     */
    @ApiModelProperty(value = "作者名称")
    @Length(max = 50, message = "作者名称长度不能超过50")
    @TableField("author")
    private String author;

    /**
     * 处理地址
     * 以http开头时直接跳转，否则与#c_application表拼接后跳转
     * http可带参数
     */
    @ApiModelProperty(value = "处理地址")
    @Length(max = 200, message = "处理地址长度不能超过200")
    @TableField("handler_url")
    private String handlerUrl;

    /**
     * 处理参数
     */
    @ApiModelProperty(value = "处理参数")
    @Length(max = 400, message = "处理参数长度不能超过400")
    @TableField("handler_params")
    private String handlerParams;

    /**
     * 是否单人处理后就标记已处理
     */
    @ApiModelProperty(value = "是否单人处理后就标记已处理")
    @TableField("is_single_handle")
    private Boolean isSingleHandle;

    /**
     * 是否删除
     * 业务数据删除后，会调用接口删除该消息
     */
    @ApiModelProperty(value = "是否删除")
    @TableField("is_delete")
    private Boolean isDelete;

    /**
     * 应用code
     */
    @ApiModelProperty(value = "应用code")
    @Length(max = 64, message = "应用code长度不能超过64")
    @TableField("app_code")
    private String appCode;

    /**
     * 应用名称
     */
    @ApiModelProperty(value = "应用名称")
    @Length(max = 255, message = "应用名称长度不能超过255")
    @TableField("app_name")
    private String appName;


    @Builder
    public MsgsCenterInfo(Long id, LocalDateTime createTime, Long createUser, LocalDateTime updateTime, Long updateUser,
                          String bizId, String bizType, MsgsCenterType msgsCenterType, String title, String content,
                          String author, String handlerUrl, String handlerParams, Boolean isSingleHandle, Boolean isDelete, String appCode, String appName) {
        this.id = id;
        this.createTime = createTime;
        this.createUser = createUser;
        this.updateTime = updateTime;
        this.updateUser = updateUser;
        this.bizId = bizId;
        this.bizType = bizType;
        this.msgsCenterType = msgsCenterType;
        this.title = title;
        this.content = content;
        this.author = author;
        this.handlerUrl = handlerUrl;
        this.handlerParams = handlerParams;
        this.isSingleHandle = isSingleHandle;
        this.isDelete = isDelete;
        this.appCode = appCode;
        this.appName = appName;
    }

}
