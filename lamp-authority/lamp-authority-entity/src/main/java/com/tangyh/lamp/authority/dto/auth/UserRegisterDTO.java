package com.tangyh.lamp.authority.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 用户注册DTO
 *
 * @author zuihou
 * @date 2019/08/06
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "UserRegisterDTO", description = "用户注册DTO")
public class UserRegisterDTO implements Serializable {

    @NotEmpty(message = "手机号不能为空")
    @ApiModelProperty(value = "手机号")
    private String mobile;

    @NotEmpty(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "验证码")
    @NotEmpty(message = "验证码不能为空")
    private String verificationCode;
}
