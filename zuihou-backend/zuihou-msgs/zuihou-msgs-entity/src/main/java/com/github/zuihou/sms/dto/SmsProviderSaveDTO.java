//package com.github.zuihou.sms.dto;
//
//import java.io.Serializable;
//
//import javax.validation.constraints.NotNull;
//
//import com.github.zuihou.sms.enumeration.ProviderType;
//
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//import lombok.experimental.Accessors;
//import org.hibernate.validator.constraints.Length;
//
///**
// * <p>
// * 实体类
// * 短信供应商
// * </p>
// *
// * @author zuihou
// * @since 2019-08-01
// */
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Accessors(chain = true)
//@ToString(callSuper = true)
//@EqualsAndHashCode(callSuper = false)
//@Builder
//@ApiModel(value = "SmsProviderSaveDTO", description = "短信供应商")
//public class SmsProviderSaveDTO implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    /**
//     * 供应商类型
//     * #ProviderType{ALI:OK,阿里云短信;TENCENT:0,腾讯云短信;BAIDU:1000,百度云短信}
//     */
//    @ApiModelProperty(value = "供应商类型")
//    @NotNull(message = "供应商类型不能为空")
//    private ProviderType providerType;
//    /**
//     * 应用编码
//     */
//    @ApiModelProperty(value = "应用编码")
//    @Length(max = 64, message = "应用编码长度不能超过64")
//    private String appId;
//    /**
//     * 应用密钥
//     */
//    @ApiModelProperty(value = "应用密钥")
//    @Length(max = 64, message = "应用密钥长度不能超过64")
//    private String appSecret;
//    /**
//     * SMS服务域名
//     * 百度、其他厂商会用
//     */
//    @ApiModelProperty(value = "SMS服务域名")
//    @Length(max = 255, message = "SMS服务域名长度不能超过255")
//    private String url;
//    /**
//     * 账号名称
//     */
//    @ApiModelProperty(value = "账号名称")
//    @Length(max = 255, message = "账号名称长度不能超过255")
//    private String name;
//    /**
//     * 第三方登录账号
//     */
//    @ApiModelProperty(value = "第三方登录账号")
//    @Length(max = 100, message = "第三方登录账号长度不能超过100")
//    private String account;
//    /**
//     * 第三方登录密码
//     */
//    @ApiModelProperty(value = "第三方登录密码")
//    @Length(max = 255, message = "第三方登录密码长度不能超过255")
//    private String password;
//    /**
//     * 描述
//     */
//    @ApiModelProperty(value = "描述")
//    @Length(max = 300, message = "描述长度不能超过300")
//    private String description;
//    /**
//     * 现有短信量
//     */
//    @ApiModelProperty(value = "现有短信量")
//    private Long existing;
//    /**
//     * 已用短信量
//     */
//    @ApiModelProperty(value = "已用短信量")
//    private Long used;
//
//}
