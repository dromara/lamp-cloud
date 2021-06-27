package com.tangyh.lamp.sms.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangyh.basic.base.entity.Entity;
import com.tangyh.basic.model.EchoVO;
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

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

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
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("e_sms_template")
@ApiModel(value = "SmsTemplate", description = "短信模板")
@AllArgsConstructor
public class SmsTemplate extends Entity<Long> implements EchoVO {

    private static final long serialVersionUID = 1L;
    @TableField(exist = false)
    private Map<String, Object> echoMap = new HashMap<>();
    /**
     * 供应商类型
     * #ProviderType{ALI:OK,阿里云短信;TENCENT:0,腾讯云短信;BAIDU:1000,百度云短信}
     */
    @ApiModelProperty(value = "供应商类型")
    @NotNull(message = "请填写供应商类型")
    @TableField(value = "provider_type")
    @Excel(name = "供应商类型", replace = {"OK_ALI", "0_TENCENT", "1000_BAIDU", "_null"})
    private ProviderType providerType;

    /**
     * 应用ID
     */
    @ApiModelProperty(value = "应用ID")
    @NotEmpty(message = "请填写应用ID")
    @Size(max = 255, message = "应用ID长度不能超过255")
    @TableField(value = "app_id", condition = LIKE)
    @Excel(name = "应用ID")
    private String appId;

    /**
     * 应用密码
     */
    @ApiModelProperty(value = "应用密码")
    @NotEmpty(message = "请填写应用密码")
    @Size(max = 255, message = "应用密码长度不能超过255")
    @TableField(value = "app_secret", condition = LIKE)
    @Excel(name = "应用密码")
    private String appSecret;

    /**
     * SMS服务域名
     * 百度、其他厂商会用
     */
    @ApiModelProperty(value = "SMS服务域名")
    @Size(max = 255, message = "SMS服务域名长度不能超过255")
    @TableField(value = "url", condition = LIKE)
    @Excel(name = "SMS服务域名")
    private String url;

    /**
     * 模板名称
     */
    @ApiModelProperty(value = "模板名称")
    @Size(max = 255, message = "模板名称长度不能超过255")
    @TableField(value = "name", condition = LIKE)
    @Excel(name = "模板名称")
    private String name;

    /**
     * 模板内容
     */
    @ApiModelProperty(value = "模板内容")
    @NotEmpty(message = "请填写模板内容")
    @Size(max = 255, message = "模板内容长度不能超过255")
    @TableField(value = "content", condition = LIKE)
    @Excel(name = "模板内容")
    private String content;

    /**
     * 模板参数
     */
    @ApiModelProperty(value = "模板参数")
    @NotEmpty(message = "请填写模板参数")
    @Size(max = 255, message = "模板参数长度不能超过255")
    @TableField(value = "template_params", condition = LIKE)
    @Excel(name = "模板参数")
    private String templateParams;

    /**
     * 模板编码
     */
    @ApiModelProperty(value = "模板编码")
    @NotEmpty(message = "请填写模板编码")
    @Size(max = 50, message = "模板编码长度不能超过50")
    @TableField(value = "template_code", condition = LIKE)
    @Excel(name = "模板编码")
    private String templateCode;

    /**
     * 签名
     */
    @ApiModelProperty(value = "签名")
    @Size(max = 100, message = "签名长度不能超过100")
    @TableField(value = "sign_name", condition = LIKE)
    @Excel(name = "签名")
    private String signName;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Size(max = 255, message = "备注长度不能超过255")
    @TableField(value = "template_describe", condition = LIKE)
    @Excel(name = "备注")
    private String templateDescribe;


    @Builder
    public SmsTemplate(Long id, Long createdBy, LocalDateTime createTime, Long updatedBy, LocalDateTime updateTime,
                       ProviderType providerType, String appId, String appSecret, String url,
                       String name, String content, String templateParams, String templateCode, String signName, String templateDescribe) {
        this.id = id;
        this.createdBy = createdBy;
        this.createTime = createTime;
        this.updatedBy = updatedBy;
        this.updateTime = updateTime;
        this.providerType = providerType;
        this.appId = appId;
        this.appSecret = appSecret;
        this.url = url;
        this.name = name;
        this.content = content;
        this.templateParams = templateParams;
        this.templateCode = templateCode;
        this.signName = signName;
        this.templateDescribe = templateDescribe;
    }

}
