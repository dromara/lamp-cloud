package top.tangyh.lamp.system.vo.update.tenant;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.basic.annotation.constraints.NotEmptyPattern;

import java.io.Serializable;

import static top.tangyh.basic.utils.ValidatorUtil.REGEX_PASSWORD;

/**
 * <p>
 * 用户密码修改VO
 * </p>
 *
 * @author zuihou
 * @since 2019-11-04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description = "用户密码修改VO")
public class DefUserPasswordUpdateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @NotNull(message = "id不能为空")
    private Long id;

    /**
     * 密码
     */
    @Schema(description = "旧密码")
    @NotEmpty(message = "请填写旧密码")
    private String oldPassword;
    /**
     * 密码
     */
    @Schema(description = "密码")
    @NotEmpty(message = "请填写密码")
    @Size(min = 6, max = 20, message = "密码长度不能小于{min}且超过{max}个字符")
    @NotEmptyPattern(regexp = REGEX_PASSWORD, message = "至少包含字母、数字、特殊字符")
    private String password;

    /**
     * 密码
     */
    @Schema(description = "确认密码")
    @NotEmpty(message = "请填写确认密码")
    @Size(min = 6, max = 20, message = "密码长度不能小于{min}且超过{max}个字符")
    private String confirmPassword;

}
