package com.tangyh.lamp.sms.dto;

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

import java.io.Serializable;
import java.time.LocalDateTime;

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
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "SmsTaskPageQuery", description = "发送任务")
public class SmsTaskPageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 短信模板
     * #e_sms_template
     */
    @ApiModelProperty(value = "短信模板")
    private Long templateId;
    /**
     * 执行状态
     * (手机号具体发送状态看sms_send_status表)
     * #TaskStatus{WAITING:等待执行;SUCCESS:执行成功;FAIL:执行失败}
     */
    @ApiModelProperty(value = "执行状态")
    private TaskStatus status;
    /**
     * 发送渠道
     * #SourceType{APP:应用;SERVICE:服务}
     */
    @ApiModelProperty(value = "发送渠道")
    private SourceType sourceType;
    @ApiModelProperty(value = "接收者手机")
    private String telNum;
    /**
     * 主题
     */
    @ApiModelProperty(value = "主题")
    private String topic;
    /**
     * 参数
     * 需要封装为{‘key’:’value’, ...}格式且key必须有序
     */
    @ApiModelProperty(value = "参数")
    private String templateParams;
    /**
     * 发送时间
     */
    @ApiModelProperty(value = "发送时间")
    private LocalDateTime sendTime;
    /**
     * 发送内容
     * 需要封装正确格式化: 您好，张三，您有一个新的快递。
     */
    @ApiModelProperty(value = "发送内容")
    private String content;
    /**
     * 是否草稿
     */
    @ApiModelProperty(value = "是否草稿")
    private Boolean draft;

}
