package com.github.zuihou.msgs.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zuihou.base.entity.Entity;
import com.github.zuihou.msgs.enumeration.MsgsBizType;
import com.github.zuihou.msgs.enumeration.MsgsCenterType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

/**
 * <p>
 * 实体类
 * 消息中心表
 * </p>
 *
 * @author zuihou
 * @since 2019-12-21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("msgs_center_info")
@ApiModel(value = "MsgsCenterInfo", description = "消息中心表")
public class MsgsCenterInfo extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 业务ID
     * 业务表的唯一id
     */
    @ApiModelProperty(value = "业务ID")
    @Length(max = 64, message = "业务ID长度不能超过64")
    @TableField(value = "biz_id", condition = LIKE)
    private String bizId;

    /**
     * 业务类型
     * #MsgsBizType{USER_LOCK:账号锁定;}
     */
    @ApiModelProperty(value = "业务类型")
    @TableField("biz_type")
    private MsgsBizType bizType;

    /**
     * 消息类型
     * #MsgsCenterType{WAIT:待办;NOTIFY:通知;PUBLICITY:公告;WARN:预警;}
     */
    @ApiModelProperty(value = "消息类型")
    @NotNull(message = "消息类型不能为空")
    @TableField("msgs_center_type")
    @Excel(name = "消息类型", replace = {"待办_WAIT", "通知_NOTIFY", "公告_PUBLICITY", "预警_WARN", "_null"})
    private MsgsCenterType msgsCenterType;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    @Length(max = 255, message = "标题长度不能超过255")
    @TableField(value = "title", condition = LIKE)
    @Excel(name = "标题", width = 30)
    private String title;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    @Length(max = 65535, message = "内容长度不能超过65535")
    @TableField("content")
    @Excel(name = "内容", width = 50)
    private String content;

    /**
     * 作者
     */
    @ApiModelProperty(value = "发布人")
    @Length(max = 50, message = "发布人长度不能超过50")
    @TableField(value = "author", condition = LIKE)
    @Excel(name = "发布人")
    private String author;

    /**
     * 处理地址
     * 以http开头时直接跳转，否则与#c_application表拼接后跳转
     * http可带参数
     */
    @ApiModelProperty(value = "处理地址")
    @Length(max = 255, message = "处理地址长度不能超过255")
    @TableField(value = "handler_url", condition = LIKE)
    private String handlerUrl;

    /**
     * 处理参数
     */
    @ApiModelProperty(value = "处理参数")
    @Length(max = 400, message = "处理参数长度不能超过400")
    @TableField(value = "handler_params", condition = LIKE)
    private String handlerParams;

    /**
     * 是否单人处理
     */
    @ApiModelProperty(value = "是否单人处理")
    @TableField("is_single_handle")
    private Boolean isSingleHandle;


    @Builder
    public MsgsCenterInfo(Long id, LocalDateTime createTime, Long createUser, LocalDateTime updateTime, Long updateUser,
                          String bizId, MsgsBizType bizType, MsgsCenterType msgsCenterType, String title, String content,
                          String author, String handlerUrl, String handlerParams, Boolean isSingleHandle) {
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
    }

}
