package com.tangyh.lamp.authority.dto.auth;

import com.tangyh.basic.base.entity.SuperEntity;
import com.tangyh.lamp.authority.enumeration.auth.ApplicationAppTypeEnum;
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

/**
 * <p>
 * 实体类
 * 应用
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
@ApiModel(value = "ApplicationUpdateDTO", description = "应用")
public class ApplicationUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @NotNull(message = "id不能为空", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 客户端ID
     */
    @ApiModelProperty(value = "客户端ID")
    @Size(max = 24, message = "客户端ID长度不能超过24")
    private String clientId;
    /**
     * 客户端密码
     */
    @ApiModelProperty(value = "客户端密码")
    @Size(max = 32, message = "客户端密码长度不能超过32")
    private String clientSecret;
    /**
     * 官网
     */
    @ApiModelProperty(value = "官网")
    @Size(max = 100, message = "官网长度不能超过100")
    private String website;
    /**
     * 应用名称
     */
    @ApiModelProperty(value = "应用名称")
    @NotEmpty(message = "应用名称不能为空")
    @Size(max = 255, message = "应用名称长度不能超过255")
    private String name;
    /**
     * 应用图标
     */
    @ApiModelProperty(value = "应用图标")
    @Size(max = 255, message = "应用图标长度不能超过255")
    private String icon;
    /**
     * 类型
     * #{SERVER:服务应用;APP:手机应用;PC:PC网页应用;WAP:手机网页应用}
     */
    @ApiModelProperty(value = "类型")
    private ApplicationAppTypeEnum appType;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Size(max = 200, message = "备注长度不能超过200")
    private String describe;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Boolean state;
}
