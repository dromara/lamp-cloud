package com.tangyh.lamp.sms.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangyh.basic.annotation.echo.Echo;
import com.tangyh.basic.base.entity.Entity;
import com.tangyh.basic.model.EchoVO;
import com.tangyh.lamp.sms.enumeration.SourceType;
import com.tangyh.lamp.sms.enumeration.TaskStatus;
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
import java.util.HashMap;
import java.util.Map;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;
import static com.tangyh.basic.utils.DateUtils.DEFAULT_DATE_TIME_FORMAT;
import static com.tangyh.lamp.common.constant.EchoConstants.FIND_NAME_BY_IDS;
import static com.tangyh.lamp.common.constant.EchoConstants.SMS_TEMPLATE_ID_CLASS;

/**
 * <p>
 * 实体类
 * 发送任务
 * </p>
 *
 * @author zuihou
 * @since 2021-06-23
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("e_sms_task")
@ApiModel(value = "SmsTask", description = "发送任务")
@AllArgsConstructor
public class SmsTask extends Entity<Long> implements EchoVO {

    private static final long serialVersionUID = 1L;
    @TableField(exist = false)
    private Map<String, Object> echoMap = new HashMap<>();
    /**
     * 短信模板
     * #e_sms_template
     */
    @ApiModelProperty(value = "短信模板")
    @NotNull(message = "请填写短信模板")
    @TableField(value = "template_id")
    @Excel(name = "短信模板")
    @Echo(api = SMS_TEMPLATE_ID_CLASS, method = FIND_NAME_BY_IDS)
    private Long templateId;

    /**
     * 执行状态
     * (手机号具体发送状态看sms_send_status表)
     * #TaskStatus{WAITING:等待执行;SUCCESS:执行成功;FAIL:执行失败}
     */
    @ApiModelProperty(value = "执行状态")
    @TableField(value = "status")
    @Excel(name = "执行状态", replace = {"等待执行_WAITING", "执行成功_SUCCESS", "执行失败_FAIL", "_null"})
    private TaskStatus status;

    /**
     * 发送渠道
     * #SourceType{APP:应用;SERVICE:服务}
     */
    @ApiModelProperty(value = "发送渠道")
    @TableField(value = "source_type")
    @Excel(name = "发送渠道", replace = {"应用_APP", "服务_SERVICE", "_null"})
    private SourceType sourceType;

    /**
     * 主题
     */
    @ApiModelProperty(value = "主题")
    @Size(max = 255, message = "主题长度不能超过255")
    @TableField(value = "topic", condition = LIKE)
    @Excel(name = "主题")
    private String topic;

    /**
     * 参数
     * 需要封装为{‘key’:’value’, ...}格式且key必须有序
     */
    @ApiModelProperty(value = "参数")
    @Size(max = 500, message = "参数长度不能超过500")
    @TableField(value = "template_params", condition = LIKE)
    @Excel(name = "参数")
    private String templateParams;

    /**
     * 发送时间
     */
    @ApiModelProperty(value = "发送时间")
    @TableField(value = "send_time")
    @Excel(name = "发送时间", format = DEFAULT_DATE_TIME_FORMAT, width = 20)
    private LocalDateTime sendTime;

    /**
     * 发送内容
     * 需要封装正确格式化: 您s好，张三，您有一个新的快递。
     */
    @ApiModelProperty(value = "发送内容")
    @Size(max = 500, message = "发送内容长度不能超过500")
    @TableField(value = "content", condition = LIKE)
    @Excel(name = "发送内容")
    private String content;

    /**
     * 是否草稿
     */
    @ApiModelProperty(value = "是否草稿")
    @TableField(value = "draft")
    @Excel(name = "是否草稿", replace = {"是_true", "否_false", "_null"})
    private Boolean draft;


    @Builder
    public SmsTask(Long id, Long createdBy, LocalDateTime createTime, Long updatedBy, LocalDateTime updateTime,
                   Long templateId, TaskStatus status, SourceType sourceType, String topic, String templateParams,
                   LocalDateTime sendTime, String content, Boolean draft) {
        this.id = id;
        this.createdBy = createdBy;
        this.createTime = createTime;
        this.updatedBy = updatedBy;
        this.updateTime = updateTime;
        this.templateId = templateId;
        this.status = status;
        this.sourceType = sourceType;
        this.topic = topic;
        this.templateParams = templateParams;
        this.sendTime = sendTime;
        this.content = content;
        this.draft = draft;
    }

}
