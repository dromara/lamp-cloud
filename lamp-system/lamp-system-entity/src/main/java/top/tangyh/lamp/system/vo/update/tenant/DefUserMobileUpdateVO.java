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

import static top.tangyh.basic.utils.ValidatorUtil.REGEX_MOBILE;

/**
 * <p>
 * 用户手机修改VO
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
@Schema(description = "用户手机修改VO")
public class DefUserMobileUpdateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 手机;1开头11位纯数字
     */
    @Schema(description = "手机")
    @Size(max = 11, message = "手机长度不能超过{max}")
    @NotEmptyPattern(regexp = REGEX_MOBILE, message = "请输入11位的手机号")
    @NotEmpty(message = "请填写手机")
    private String mobile;


    @Schema(description = "验证码")
    @Size(max = 10, message = "验证码长度不能超过{max}")
    @NotEmpty(message = "请填写验证码")
    private String code;

    @Schema(description = "消息模板")
    @NotEmpty(message = "请填写消息模板")
    private String templateCode;
}
