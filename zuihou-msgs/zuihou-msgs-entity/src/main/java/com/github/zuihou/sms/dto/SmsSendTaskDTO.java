package com.github.zuihou.sms.dto;

import com.alibaba.fastjson.JSONObject;
import com.github.zuihou.sms.enumeration.TemplateCodeType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 短信发送任务
 *
 * @author zuihou
 * @date 2018/12/24
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "SmsSendTask", description = "短信发送DTO")
public class SmsSendTaskDTO {

    /**
     * 短信模板id #sms_template
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "短信模板Code #sms_template")
    @NotEmpty(message = "自定义供应商编号不能为空")
    private TemplateCodeType customCode;

    /**
     * 接收者手机号 群发用英文逗号分割.
     * 支持2种格式:
     * 1: 手机号,手机号
     * 2: 姓名<手机号>,姓名<手机号>
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "接收者手机号 群发用英文逗号分割.\n" +
            "支持2种格式:\n" +
            "1: 手机号,手机号 \n" +
            "2: 姓名<手机号>,姓名<手机号>")
    @Length(max = 65535, message = "接收人长度不能超过65535")
    @NotEmpty(message = "接收人不能为空")
    private String receiver;

    /**
     * 短信主题
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "短信主题")
    @Length(max = 255, message = "短信主题不能超过255")
    private String topic;

    /**
     * 短信模板参数
     * 需要封装为{"key":"value", ...}格式，且key必须有序
     * <p>
     * java代码中请使用 JSONObject.parseObject(xxx, Feature.OrderedField); 进行解析
     * java代码中请使用 JSONObject obj = new JSONObject(true); 来设置有序的key
     *
     * @auth 使用时，请咨询 zuihou
     * @mbggenerated
     */
    @ApiModelProperty(value = "短信模板参数")
    @Length(max = 500, message = "短信模板参数不能超过500")
    @NotNull(message = "短信模板参数不能为空")
    private JSONObject templateParam;

    /**
     * 短信发送时间
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "短信发送时间")
    private LocalDateTime sendTime;

    /**
     * 发送内容
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "发送内容")
    @Length(max = 500, message = "发送内容不能超过450")
    private String context;
    /**
     * 是否为草稿
     * #BooleanStatus
     */
    @ApiModelProperty(value = "是否为草稿")
    private Boolean draft;

    /**
     * 最少传递的参数
     *
     * @param customCode
     * @param receiver
     * @param templateParam
     * @return
     */
    public SmsSendTaskDTO build(TemplateCodeType customCode, String receiver, JSONObject templateParam) {
        return SmsSendTaskDTO.builder()
                .customCode(customCode)
                .receiver(receiver)
                .templateParam(templateParam)
                .build();
    }

}
