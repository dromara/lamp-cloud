package top.tangyh.lamp.sms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.lamp.sms.enumeration.ProviderType;

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
@Schema(description = "短信模板")
public class SmsTemplatePageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 供应商类型
     * #ProviderType{ALI:OK,阿里云短信;TENCENT:0,腾讯云短信;BAIDU:1000,百度云短信}
     */
    @Schema(description = "供应商类型")
    private ProviderType providerType;
    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    private String appId;
    /**
     * 应用密码
     */
    @Schema(description = "应用密码")
    private String appSecret;
    /**
     * SMS服务域名
     * 百度、其他厂商会用
     */
    @Schema(description = "SMS服务域名")
    private String url;
    /**
     * 模板名称
     */
    @Schema(description = "模板名称")
    private String name;
    /**
     * 模板内容
     */
    @Schema(description = "模板内容")
    private String content;
    /**
     * 模板参数
     */
    @Schema(description = "模板参数")
    private String templateParams;
    /**
     * 模板编码
     */
    @Schema(description = "模板编码")
    private String templateCode;
    /**
     * 签名
     */
    @Schema(description = "签名")
    private String signName;
    /**
     * 备注
     */
    @Schema(description = "备注")
    private String templateDescribe;

}
