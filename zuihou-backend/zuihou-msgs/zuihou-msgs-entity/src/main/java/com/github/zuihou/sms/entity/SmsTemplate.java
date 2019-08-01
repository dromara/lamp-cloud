package com.github.zuihou.sms.entity;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zuihou.base.entity.Entity;

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
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sms_template")
@ApiModel(value = "SmsTemplate", description = "短信模板")
public class SmsTemplate extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 供应商ID
     * #sms_provider
     */
    @ApiModelProperty(value = "供应商ID")
    @NotNull(message = "供应商ID不能为空")
    @TableField("provider_id")
    private Long providerId;

    /**
     * 模板编码
     * 用于api发送
     */
    @ApiModelProperty(value = "模板编码")
    @NotEmpty(message = "模板编码不能为空")
    @Length(max = 20, message = "模板编码长度不能超过20")
    @TableField("custom_code")
    private String customCode;

    /**
     * 模板名称
     */
    @ApiModelProperty(value = "模板名称")
    @Length(max = 255, message = "模板名称长度不能超过255")
    @TableField("name")
    private String name;

    /**
     * 模板内容
     */
    @ApiModelProperty(value = "模板内容")
    @NotEmpty(message = "模板内容不能为空")
    @Length(max = 255, message = "模板内容长度不能超过255")
    @TableField("content")
    private String content;

    /**
     * 模板参数
     */
    @ApiModelProperty(value = "模板参数")
    @NotEmpty(message = "模板参数不能为空")
    @Length(max = 255, message = "模板参数长度不能超过255")
    @TableField("template_params")
    private String templateParams;

    /**
     * 模板CODE
     */
    @ApiModelProperty(value = "模板CODE")
    @Length(max = 50, message = "模板CODE长度不能超过50")
    @TableField("template_code")
    private String templateCode;

    /**
     * 模板签名名称
     */
    @ApiModelProperty(value = "模板签名名称")
    @Length(max = 100, message = "模板签名名称长度不能超过100")
    @TableField("sign_name")
    private String signName;

    /**
     * 模板描述
     */
    @ApiModelProperty(value = "模板描述")
    @Length(max = 255, message = "模板描述长度不能超过255")
    @TableField("template_describe")
    private String templateDescribe;


    @Builder
    public SmsTemplate(Long id, Long createUser, LocalDateTime createTime, Long updateUser, LocalDateTime updateTime,
                       Long providerId, String customCode, String name, String content, String templateParams,
                       String templateCode, String signName, String templateDescribe) {
        this.id = id;
        this.createUser = createUser;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
        this.providerId = providerId;
        this.customCode = customCode;
        this.name = name;
        this.content = content;
        this.templateParams = templateParams;
        this.templateCode = templateCode;
        this.signName = signName;
        this.templateDescribe = templateDescribe;
    }

}
