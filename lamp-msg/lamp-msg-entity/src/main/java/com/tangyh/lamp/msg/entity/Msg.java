package com.tangyh.lamp.msg.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangyh.basic.base.entity.Entity;
import com.tangyh.lamp.msg.enumeration.MsgBizType;
import com.tangyh.lamp.msg.enumeration.MsgType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

/**
 * <p>
 * 实体类
 * 消息表
 * </p>
 *
 * @author zuihou
 * @since 2020-11-21
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("e_msg")
@ApiModel(value = "Msg", description = "消息表")
@AllArgsConstructor
public class Msg extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 业务ID
     */
    @ApiModelProperty(value = "业务ID")
    @Size(max = 64, message = "业务ID长度不能超过64")
    @TableField(value = "biz_id", condition = LIKE)
    @Excel(name = "业务ID")
    private String bizId;

    /**
     * 业务类型
     * #MsgBizType{USER_LOCK:账号锁定;USER_REG:账号申请;WORK_APPROVAL:考勤审批;}
     */
    @ApiModelProperty(value = "业务类型")
    @TableField("biz_type")
    @Excel(name = "业务类型", replace = {"账号锁定_USER_LOCK", "账号申请_USER_REG", "考勤审批_WORK_APPROVAL", "_null"})
    private MsgBizType bizType;

    /**
     * 消息类型
     * #MsgType{WAIT:待办;NOTIFY:通知;PUBLICITY:公告;WARN:预警;}
     */
    @ApiModelProperty(value = "消息类型")
    @NotNull(message = "消息类型不能为空")
    @TableField("msg_type")
    @Excel(name = "消息类型", replace = {"待办_WAIT", "通知_NOTIFY", "公告_PUBLICITY", "预警_WARN", "_null"})
    private MsgType msgType;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    @Size(max = 255, message = "标题长度不能超过255")
    @TableField(value = "title", condition = LIKE)
    @Excel(name = "标题")
    private String title;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    @Size(max = 65535, message = "内容长度不能超过65535")
    @TableField("content")
    @Excel(name = "内容")
    private String content;

    /**
     * 发布人
     */
    @ApiModelProperty(value = "发布人")
    @Size(max = 50, message = "发布人长度不能超过50")
    @TableField(value = "author", condition = LIKE)
    @Excel(name = "发布人")
    private String author;

    /**
     * 处理地址
     * 以http开头时直接跳转，否则与#c_application表拼接后跳转http可带参数
     */
    @ApiModelProperty(value = "处理地址")
    @Size(max = 255, message = "处理地址长度不能超过255")
    @TableField(value = "handler_url", condition = LIKE)
    @Excel(name = "处理地址")
    private String handlerUrl;

    /**
     * 处理参数
     */
    @ApiModelProperty(value = "处理参数")
    @Size(max = 500, message = "处理参数长度不能超过500")
    @TableField(value = "handler_params", condition = LIKE)
    @Excel(name = "处理参数")
    private String handlerParams;

    /**
     * 是否单人处理
     */
    @ApiModelProperty(value = "是否单人处理")
    @TableField("is_single_handle")
    @Excel(name = "是否单人处理", replace = {"是_true", "否_false", "_null"})
    private Boolean isSingleHandle;


    @Builder
    public Msg(Long id, LocalDateTime createTime, Long createdBy, LocalDateTime updateTime, Long updatedBy,
               String bizId, MsgBizType bizType, MsgType msgType, String title, String content,
               String author, String handlerUrl, String handlerParams, Boolean isSingleHandle) {
        this.id = id;
        this.createTime = createTime;
        this.createdBy = createdBy;
        this.updateTime = updateTime;
        this.updatedBy = updatedBy;
        this.bizId = bizId;
        this.bizType = bizType;
        this.msgType = msgType;
        this.title = title;
        this.content = content;
        this.author = author;
        this.handlerUrl = handlerUrl;
        this.handlerParams = handlerParams;
        this.isSingleHandle = isSingleHandle;
    }

}
