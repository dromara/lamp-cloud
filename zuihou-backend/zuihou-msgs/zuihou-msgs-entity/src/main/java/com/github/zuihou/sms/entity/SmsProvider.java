package com.github.zuihou.sms.entity;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zuihou.base.entity.Entity;
import com.github.zuihou.sms.enumeration.ProviderType;

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
 * 短信供应商
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
@TableName("sms_provider")
@ApiModel(value = "SmsProvider", description = "短信供应商")
public class SmsProvider extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 供应商类型
     * #ProviderType{ALI:OK,阿里云短信;TENCENT:0,腾讯云短信;BAIDU:1000,百度云短信}
     */
    @ApiModelProperty(value = "供应商类型")
    @NotNull(message = "供应商类型不能为空")
    @TableField("provider_type")
    private ProviderType providerType;

    /**
     * 应用编码
     */
    @ApiModelProperty(value = "应用编码")
    @Length(max = 64, message = "应用编码长度不能超过64")
    @TableField("app_id")
    private String appId;

    /**
     * 应用密钥
     */
    @ApiModelProperty(value = "应用密钥")
    @Length(max = 64, message = "应用密钥长度不能超过64")
    @TableField("app_secret")
    private String appSecret;

    /**
     * SMS服务域名
     * 百度、其他厂商会用
     */
    @ApiModelProperty(value = "SMS服务域名")
    @Length(max = 255, message = "SMS服务域名长度不能超过255")
    @TableField("url")
    private String url;

    /**
     * 账号名称
     */
    @ApiModelProperty(value = "账号名称")
    @Length(max = 255, message = "账号名称长度不能超过255")
    @TableField("name")
    private String name;

    /**
     * 第三方登录账号
     */
    @ApiModelProperty(value = "第三方登录账号")
    @Length(max = 100, message = "第三方登录账号长度不能超过100")
    @TableField("account")
    private String account;

    /**
     * 第三方登录密码
     */
    @ApiModelProperty(value = "第三方登录密码")
    @Length(max = 255, message = "第三方登录密码长度不能超过255")
    @TableField("password")
    private String password;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @Length(max = 300, message = "描述长度不能超过300")
    @TableField("description")
    private String description;

    /**
     * 现有短信量
     */
    @ApiModelProperty(value = "现有短信量")
    @TableField("existing")
    private Long existing;

    /**
     * 已用短信量
     */
    @ApiModelProperty(value = "已用短信量")
    @TableField("used")
    private Long used;


    @Builder
    public SmsProvider(Long id, Long createUser, LocalDateTime createTime, Long updateUser, LocalDateTime updateTime,
                       ProviderType providerType, String appId, String appSecret, String url, String name,
                       String account, String password, String description, Long existing, Long used) {
        this.id = id;
        this.createUser = createUser;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
        this.providerType = providerType;
        this.appId = appId;
        this.appSecret = appSecret;
        this.url = url;
        this.name = name;
        this.account = account;
        this.password = password;
        this.description = description;
        this.existing = existing;
        this.used = used;
    }

}
