package top.tangyh.lamp.system.vo.query.tenant;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 忘记密码  入参
 *
 * @author tangyh
 * @since 2025/6/30 12:52
 */
@Data
@Schema(title = "ForgetPasswordDto", description = "忘记密码")
public class ForgetPasswordDto {

    @NotEmpty(message = "请填写验证码")
    @Schema(description = "验证码")
    private String code;

    @Schema(description = "用户名")
    @NotEmpty(message = "请填写用户名")
    private String username;
    @NotEmpty(message = "请填写密码")
    @Schema(description = "密码")
    private String password;

    @NotEmpty(message = "请填写手机号")
    @Schema(description = "手机号")
    private String mobile;
}
