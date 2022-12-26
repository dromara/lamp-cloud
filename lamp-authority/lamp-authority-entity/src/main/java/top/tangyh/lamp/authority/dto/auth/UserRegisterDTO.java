package top.tangyh.lamp.authority.dto.auth;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

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
@Schema(description = "用户注册DTO")
public class UserRegisterDTO implements Serializable {

    @NotEmpty(message = "手机号不能为空")
    @Schema(description = "手机号")
    private String mobile;

    @NotEmpty(message = "密码不能为空")
    @Schema(description = "密码")
    private String password;

    @Schema(description = "验证码")
    @NotEmpty(message = "验证码不能为空")
    private String verificationCode;
}
