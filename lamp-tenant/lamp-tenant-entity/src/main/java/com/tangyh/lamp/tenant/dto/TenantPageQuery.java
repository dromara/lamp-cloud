package com.tangyh.lamp.tenant.dto;

import com.tangyh.lamp.tenant.enumeration.TenantConnectTypeEnum;
import com.tangyh.lamp.tenant.enumeration.TenantStatusEnum;
import com.tangyh.lamp.tenant.enumeration.TenantTypeEnum;
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
import java.time.LocalDateTime;

/**
 * <p>
 * 实体类
 * 企业
 * </p>
 *
 * @author zuihou
 * @since 2020-11-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "TenantPageQuery", description = "企业")
public class TenantPageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 企业编码
     */
    @ApiModelProperty(value = "企业编码")
    private String code;
    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    private String name;
    /**
     * 类型
     * #{CREATE:创建;REGISTER:注册}
     */
    @ApiModelProperty(value = "类型")
    private TenantTypeEnum type;
    /**
     * 连接类型
     * #TenantConnectTypeEnum{LOCAL:本地;REMOTE:远程}
     */
    @ApiModelProperty(value = "连接类型")
    private TenantConnectTypeEnum connectType;
    /**
     * 状态
     * #{NORMAL:正常;WAIT_INIT:待初始化;FORBIDDEN:禁用;WAITING:待审核;REFUSE:拒绝;DELETE:已删除}
     */
    @ApiModelProperty(value = "状态")
    private TenantStatusEnum status;
    /**
     * 内置
     */
    @ApiModelProperty(value = "内置")
    private Boolean readonly;
    /**
     * 责任人
     */
    @ApiModelProperty(value = "责任人")
    private String duty;
    /**
     * 有效期
     * 为空表示永久
     */
    @ApiModelProperty(value = "有效期")
    private LocalDateTime expirationTime;
    /**
     * logo地址
     */
    @ApiModelProperty(value = "logo地址")
    private String logo;
    /**
     * 企业简介
     */
    @ApiModelProperty(value = "企业简介")
    private String describe;

}
