package top.tangyh.lamp.authority.dto.auth;

import top.tangyh.lamp.model.enumeration.Sex;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
@Schema(description="全局账号")
public class GlobalUserSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 企业编码
     */
    @Schema(description="企业编码")
    @NotEmpty(message = "企业编码不能为空")
    @Size(max = 10, message = "企业编码长度不能超过10")
    private String tenantCode;
    /**
     * 账号
     */
    @Schema(description="账号")
    @NotEmpty(message = "账号不能为空")
    @Size(max = 30, message = "账号长度不能超过30")
    private String account;
    /**
     * 手机
     */
    @Schema(description="手机")
    @Size(max = 20, message = "手机长度不能超过20")
    private String mobile;
    /**
     * 姓名
     */
    @Schema(description="姓名")
    @Size(max = 50, message = "姓名长度不能超过20")
    private String name;
    @Schema(description="性别")
    private Sex sex;
    /**
     * 邮箱
     */
    @Schema(description="邮箱")
    @Size(max = 255, message = "邮箱长度不能超过255")
    private String email;
    /**
     * 密码
     */
    @Schema(description="密码")
    @Size(max = 64, message = "密码长度不能超过64")
    @NotEmpty(message = "密码不能为空")
    private String password;
    @Schema(description="确认密码")
    @Size(max = 64, message = "确认密码长度不能超过76")
    @NotEmpty(message = "确认密码不能为空")
    private String confirmPassword;

}
