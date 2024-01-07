package top.tangyh.lamp.system.vo.update.tenant;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
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

import static top.tangyh.basic.utils.ValidatorUtil.REGEX_EMAIL;

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
@Schema(description = "用户邮箱修改VO")
public class DefUserEmailUpdateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "验证码")
    @Size(max = 10, message = "验证码长度不能超过{max}")
    @NotEmpty(message = "请填写验证码")
    private String code;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    @Size(max = 255, message = "邮箱长度不能超过{max}")
    @NotEmpty(message = "请填写邮箱")
    @NotEmptyPattern(regexp = REGEX_EMAIL, message = "请输入正确的邮箱地址")
    private String email;


    @Schema(description = "消息模板")
    @NotEmpty(message = "请填写消息模板")
    private String templateCode;
}
