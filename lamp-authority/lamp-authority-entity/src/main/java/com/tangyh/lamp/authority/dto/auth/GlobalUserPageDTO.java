package com.tangyh.lamp.authority.dto.auth;

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
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 全局账号
 * </p>
 *
 * @author zuihou
 * @since 2019-10-25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "GlobalUserSaveDTO", description = "全局账号")
public class GlobalUserPageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    @NotEmpty(message = "租户编号不能为空")
    @Size(max = 10, message = "租户编号长度不能超过10")
    private String tenantCode;
    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    @NotEmpty(message = "账号不能为空")
    @Size(max = 30, message = "账号长度不能超过30")
    private String account;
    /**
     * 手机
     */
    @ApiModelProperty(value = "手机")
    @Size(max = 20, message = "手机长度不能超过20")
    private String mobile;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @Size(max = 50, message = "姓名长度不能超过20")
    private String name;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    @Size(max = 255, message = "邮箱长度不能超过255")
    private String email;

}
