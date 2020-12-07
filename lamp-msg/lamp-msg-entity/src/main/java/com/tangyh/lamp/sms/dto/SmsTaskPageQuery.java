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
 * @since 2020-11-21
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
     * 模板ID
     * #e_sms_template
     */
    @ApiModelProperty(value = "模板ID")
    private Long templateId;
    /**
     * 执行状态
     * (手机号具体发送状态看sms_send_status表)
     * #TaskStatus{WAITING:等待执行;SUCCESS:执行成功;FAIL:执行失败}
     */
    @ApiModelProperty(value = "执行状态")
    private TaskStatus status;
    /**
     * 来源类型
     * #SourceType{APP:应用;SERVICE:服务}
     */
    @ApiModelProperty(value = "来源类型")
    private SourceType sourceType;
    /**
     * 接收者手机号
     * 群发用英文逗号分割.
     * 支持2种 格式:1: 手机号,手机号  格式2: 姓名<手机号>,姓名<手机号>
     */
    @ApiModelProperty(value = "接收者手机号")
    private String receiver;
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
