package com.github.zuihou.sms.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.github.zuihou.base.entity.SuperEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

/**
 * <p>
 * 实体类
 * 短信模板
 * </p>
 *
 * @author zuihou
 * @since 2019-08-01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "SmsTemplateUpdateDTO", description = "短信模板")
public class SmsTemplateUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @NotNull(message = "id不能为空", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 供应商ID
     * #sms_provider
     */
    @ApiModelProperty(value = "供应商ID")
    @NotNull(message = "供应商ID不能为空")
    private Long providerId;
    /**
     * 模板编码
     * 用于api发送
     */
    @ApiModelProperty(value = "模板编码")
    @NotEmpty(message = "模板编码不能为空")
    @Length(max = 20, message = "模板编码长度不能超过20")
    private String customCode;
    /**
     * 模板名称
     */
    @ApiModelProperty(value = "模板名称")
    @Length(max = 255, message = "模板名称长度不能超过255")
    private String name;
    /**
     * 模板内容
     */
    @ApiModelProperty(value = "模板内容")
    @NotEmpty(message = "模板内容不能为空")
    @Length(max = 255, message = "模板内容长度不能超过255")
    private String content;
    /**
     * 模板参数
     */
    @ApiModelProperty(value = "模板参数")
    @Length(max = 255, message = "模板参数长度不能超过255")
    private String templateParams;
    /**
     * 模板CODE
     */
    @ApiModelProperty(value = "模板CODE")
    @Length(max = 50, message = "模板CODE长度不能超过50")
    private String templateCode;
    /**
     * 模板签名名称
     */
    @ApiModelProperty(value = "模板签名名称")
    @Length(max = 100, message = "模板签名名称长度不能超过100")
    private String signName;
    /**
     * 模板描述
     */
    @ApiModelProperty(value = "模板描述")
    @Length(max = 255, message = "模板描述长度不能超过255")
    private String templateDescribe;

}
