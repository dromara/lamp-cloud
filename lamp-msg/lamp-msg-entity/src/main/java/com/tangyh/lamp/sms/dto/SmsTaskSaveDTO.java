package com.tangyh.lamp.sms.dto;

import com.tangyh.lamp.sms.enumeration.SourceType;
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
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

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
@ApiModel(value = "SmsTaskSaveDTO", description = "发送任务")
public class SmsTaskSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(hidden = true)
    private SourceType sourceType;

    /**
     * 短信模板
     * #e_sms_template
     */
    @ApiModelProperty(value = "短信模板")
    @NotNull(message = "请填写短信模板")
    private Long templateId;

    @ApiModelProperty(value = "接收者手机")
    @Size(min = 1, message = "请填写接收者手机")
    private List<String> telNum;

    /**
     * 主题
     */
    @ApiModelProperty(value = "主题")
    @Size(max = 255, message = "主题长度不能超过255")
    private String topic;
    /**
     * 参数
     * 需要封装为{‘key’:’value’, ...}格式且key必须有序
     */
    @ApiModelProperty(value = "参数")
    private LinkedHashMap<String, String> templateParam;

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


    /**
     * 最少传递的参数
     *
     * @param templateParam 模版参数
     * @return 短信任务
     */
    public SmsTaskSaveDTO build(Long templateId, LinkedHashMap templateParam, String... telNum) {
        return SmsTaskSaveDTO.builder()
                .sourceType(SourceType.SERVICE)
                .templateId(templateId)
                .telNum(Arrays.asList(telNum))
                .templateParam(templateParam)
                .build();
    }

}
