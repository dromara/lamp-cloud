package top.tangyh.lamp.authority.dto.auth;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotEmpty;

/**
 * 登录参数
 *
 * @author zuihou
 * @date 2020年01月05日22:18:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description="登录参数")
public class LoginParamDTO {
    @Schema(description="验证码KEY")
    private String key;
    @Schema(description="验证码")
    private String code;

    @Schema(description="账号")
    private String account;
    @Schema(description="密码")
    private String password;

    /**
     * password: 账号密码
     * refresh_token: 刷新token
     * captcha: 验证码
     */
    @Schema(description="授权类型", example = "captcha", allowableValues = "captcha,refresh_token,password")
    @NotEmpty(message = "授权类型不能为空")
    private String grantType;

    /**
     * 前端界面点击清空缓存时调用
     */
    @Schema(description="刷新token")
    private String refreshToken;
}
