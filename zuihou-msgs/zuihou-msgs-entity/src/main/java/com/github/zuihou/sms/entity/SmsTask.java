package com.github.zuihou.sms.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zuihou.base.entity.Entity;
import com.github.zuihou.sms.enumeration.SourceType;
import com.github.zuihou.sms.enumeration.TaskStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;
import static com.github.zuihou.utils.DateUtils.DEFAULT_DATE_TIME_FORMAT;

/**
 * <p>
 * 实体类
 * 发送任务
 * 所有的短息发送调用，都视为是一次短信任务，任务表只保存数据和执行状态等信息，
 * 具体的发送状态查看发送状态（#sms_send_status）表
 * </p>
 *
 * @author zuihou
 * @since 2019-11-22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sms_task")
@ApiModel(value = "SmsTask", description = "发送任务")
public class SmsTask extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 模板ID
     * #sms_template
     */
    @ApiModelProperty(value = "模板ID")
    @NotNull(message = "模板ID不能为空")
    @TableField("template_id")
    private Long templateId;

    /**
     * 执行状态
     * (手机号具体发送状态看sms_send_status表)
     * #TaskStatus{WAITING:等待执行;SUCCESS:执行成功;FAIL:执行失败}
     */
    @ApiModelProperty(value = "执行状态")
    @TableField("status")
    @Excel(name = "执行状态", replace = {"等待执行_WAITING", "执行成功_SUCCESS", "执行失败_FAIL", "_null"})
    private TaskStatus status;

    /**
     * 来源类型
     * #SourceType{APP:应用;SERVICE:服务}
     */
    @ApiModelProperty(value = "来源类型")
    @TableField("source_type")
    private SourceType sourceType;

    /**
     * 接收者手机号
     * 群发用英文逗号分割.
     * 支持2种格式:
     * 1: 手机号,手机号
     * 2: 姓名<手机号>,姓名<手机号>
     */
    @ApiModelProperty(value = "接收者手机号")
    @Length(max = 65535, message = "接收者手机号长度不能超过65535")
    @TableField("receiver")
    @Excel(name = "接收者手机号", width = 20)
    private String receiver;

    /**
     * 主题
     */
    @ApiModelProperty(value = "主题")
    @Length(max = 255, message = "主题长度不能超过255")
    @TableField(value = "topic", condition = LIKE)
    @Excel(name = "主题", width = 20)
    private String topic;

    /**
     * 参数
     * 需要封装为{‘key’:’value’, ...}格式
     * 且key必须有序
     */
    @ApiModelProperty(value = "参数")
    @Length(max = 500, message = "参数长度不能超过500")
    @TableField(value = "template_params", condition = LIKE)
    private String templateParams;

    /**
     * 发送时间
     */
    @ApiModelProperty(value = "发送时间")
    @TableField("send_time")
    @Excel(name = "发送时间", format = DEFAULT_DATE_TIME_FORMAT, width = 20)
    private LocalDateTime sendTime;

    /**
     * 发送内容
     * 需要封装正确格式化: 您好，张三，您有一个新的快递。
     */
    @ApiModelProperty(value = "发送内容")
    @Length(max = 500, message = "发送内容长度不能超过500")
    @TableField(value = "content", condition = LIKE)
    @Excel(name = "发送内容", width = 20)
    private String content;

    @ApiModelProperty(value = "是否草稿")
    @TableField(value = "draft")
    @Excel(name = "是否草稿", replace = {"是_true", "否_false", "_null"})
    private Boolean draft;

    @Builder
    public SmsTask(Long id, Long createUser, LocalDateTime createTime, Long updateUser, LocalDateTime updateTime,
                   Long templateId, TaskStatus status, SourceType sourceType, String receiver, String topic,
                   String templateParams, LocalDateTime sendTime, String content, Boolean draft) {
        this.id = id;
        this.createUser = createUser;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
        this.templateId = templateId;
        this.status = status;
        this.sourceType = sourceType;
        this.receiver = receiver;
        this.topic = topic;
        this.templateParams = templateParams;
        this.sendTime = sendTime;
        this.draft = draft;
        this.content = content;
    }

}
