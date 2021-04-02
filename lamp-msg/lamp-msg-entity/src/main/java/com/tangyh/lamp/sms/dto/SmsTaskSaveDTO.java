package com.tangyh.lamp.sms.dto;

import com.alibaba.fastjson.JSONObject;
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
@ApiModel(value = "SmsTaskSaveDTO", description = "发送任务")
public class SmsTaskSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 模板ID
     * #e_sms_template
     */
    @ApiModelProperty(value = "模板ID")
    @NotNull(message = "模板ID不能为空")
    private Long templateId;
    /**
     * 接收者手机号
     * 群发用英文逗号分割.
     * 支持2种 格式:1: 手机号,手机号  格式2: 姓名<手机号>,姓名<手机号>
     */
    @ApiModelProperty(value = "接收者手机号")
    @Size(max = 65535, message = "接收者手机号长度不能超过65,535")
    private String receiver;
    /**
     * 主题
     */
    @ApiModelProperty(value = "主题")
    @Size(max = 255, message = "主题长度不能超过255")
    private String topic;
    /**
     * 参数
     * 需要封装为{‘key’:’value’, ...}格式
     * 且key必须有序
     */
    @ApiModelProperty(value = "参数")
    private JSONObject templateParam = new JSONObject(true);
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
    @Size(max = 500, message = "发送内容长度不能超过500")
    private String content;
    /**
     * 是否草稿
     */
    @ApiModelProperty(value = "是否草稿")
    private Boolean draft;

}
