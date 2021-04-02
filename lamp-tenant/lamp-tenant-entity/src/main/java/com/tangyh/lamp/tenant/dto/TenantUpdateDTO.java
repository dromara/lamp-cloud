package com.tangyh.lamp.tenant.dto;

import com.tangyh.basic.base.entity.SuperEntity;
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

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 实体类
 * 企业
 * </p>
 *
 * @author zuihou
 * @since 2020-11-19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "TenantUpdateDTO", description = "企业")
public class TenantUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @NotNull(message = "id不能为空", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 企业编码
     */
    @ApiModelProperty(value = "企业编码")
    @NotEmpty(message = "企业编码不能为空")
    @Size(max = 20, message = "企业编码长度不能超过20")
    private String code;
    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    @Size(max = 255, message = "企业名称长度不能超过255")
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
    @Size(max = 50, message = "责任人长度不能超过50")
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
    @Size(max = 255, message = "logo地址长度不能超过255")
    private String logo;
    /**
     * 企业简介
     */
    @ApiModelProperty(value = "企业简介")
    @Size(max = 255, message = "企业简介长度不能超过255")
    private String describe;
}
