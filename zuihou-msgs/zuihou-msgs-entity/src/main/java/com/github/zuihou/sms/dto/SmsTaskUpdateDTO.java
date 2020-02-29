package com.github.zuihou.sms.dto;

import com.alibaba.fastjson.JSONObject;
import com.github.zuihou.base.entity.SuperEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

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
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "SmsTaskUpdateDTO", description = "发送任务 所有的短息发送调用，都视为是一次短信任务，任务表只保存数据和执行状态等信息， 具体的发送状态查看发送状态（#sms_send_status）表")
public class SmsTaskUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @NotNull(message = "id不能为空", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 模板ID
     * #sms_template
     */
    @ApiModelProperty(value = "模板ID")
    @NotNull(message = "模板ID不能为空")
    private Long templateId;
    /**
     * 接收者手机号
     * 群发用英文逗号分割.
     * 支持2种格式:
     * 1: 手机号,手机号
     * 2: 姓名<手机号>,姓名<手机号>
     */
    @ApiModelProperty(value = "接收者手机号")
    @Length(max = 65535, message = "接收者手机号长度不能超过65,535")
    private String receiver;
    /**
     * 主题
     */
    @ApiModelProperty(value = "主题")
    @Length(max = 255, message = "主题长度不能超过255")
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
    @Length(max = 500, message = "发送内容长度不能超过500")
    private String content;

    private Boolean draft;

}
