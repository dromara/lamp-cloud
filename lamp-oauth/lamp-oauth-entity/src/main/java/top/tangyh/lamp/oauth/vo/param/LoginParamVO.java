package top.tangyh.lamp.oauth.vo.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.lamp.oauth.enumeration.GrantType;

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
@Schema(description = "登录参数")
public class LoginParamVO {
    @Schema(description = "验证码KEY")
    private String key;
    @Schema(description = "验证码")
    private String code;

    @Schema(description = "用户名")
    private String username;
    @Schema(description = "密码")
    private String password;

    @Schema(description = "手机号")
    private String mobile;

    /**
     * password: 账号密码
     * refresh_token: 刷新token
     * captcha: 验证码
     */
    @Schema(description = "授权类型", example = "CAPTCHA", allowableValues = "CAPTCHA,REFRESH_TOKEN,PASSWORD,MOBILE")
    @NotNull(message = "授权类型不能为空")
    private GrantType grantType;

    /**
     * 前端界面点击清空缓存时调用
     */
    @Schema(description = "刷新token")
    private String refreshToken;
}
