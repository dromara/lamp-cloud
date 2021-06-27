package com.tangyh.lamp.sms.dto;

import com.tangyh.lamp.sms.enumeration.ProviderType;
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

/**
 * <p>
 * 实体类
 * 短信模板
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
@ApiModel(value = "SmsTemplatePageQuery", description = "短信模板")
public class SmsTemplatePageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 供应商类型
     * #ProviderType{ALI:OK,阿里云短信;TENCENT:0,腾讯云短信;BAIDU:1000,百度云短信}
     */
    @ApiModelProperty(value = "供应商类型")
    private ProviderType providerType;
    /**
     * 应用ID
     */
    @ApiModelProperty(value = "应用ID")
    private String appId;
    /**
     * 应用密码
     */
    @ApiModelProperty(value = "应用密码")
    private String appSecret;
    /**
     * SMS服务域名
     * 百度、其他厂商会用
     */
    @ApiModelProperty(value = "SMS服务域名")
    private String url;
    /**
     * 模板名称
     */
    @ApiModelProperty(value = "模板名称")
    private String name;
    /**
     * 模板内容
     */
    @ApiModelProperty(value = "模板内容")
    private String content;
    /**
     * 模板参数
     */
    @ApiModelProperty(value = "模板参数")
    private String templateParams;
    /**
     * 模板编码
     */
    @ApiModelProperty(value = "模板编码")
    private String templateCode;
    /**
     * 签名
     */
    @ApiModelProperty(value = "签名")
    private String signName;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String templateDescribe;

}
