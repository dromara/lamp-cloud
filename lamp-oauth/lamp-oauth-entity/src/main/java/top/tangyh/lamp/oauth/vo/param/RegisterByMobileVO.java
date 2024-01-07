package top.tangyh.lamp.oauth.vo.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.basic.annotation.constraints.NotEmptyPattern;

import static top.tangyh.basic.utils.ValidatorUtil.REGEX_MOBILE;

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
@Schema(description = "注册")
public class RegisterByMobileVO extends RegisterVO {

    @Schema(description = "登录手机号")
    @Size(max = 11, message = "登录手机号长度不能超过{max}")
    @NotEmptyPattern(regexp = REGEX_MOBILE, message = "请输入11位合法的手机号")
    @NotEmpty(message = "请填写登录手机号")
    private String mobile;

}
