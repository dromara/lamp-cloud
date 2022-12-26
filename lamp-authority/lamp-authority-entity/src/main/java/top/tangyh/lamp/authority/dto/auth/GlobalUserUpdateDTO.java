package top.tangyh.lamp.authority.dto.auth;

import top.tangyh.basic.base.entity.SuperEntity;
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
import jakarta.validation.constraints.NotNull;
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
public class GlobalUserUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description="主键")
    @NotNull(message = "id不能为空", groups = SuperEntity.Update.class)
    private Long id;
    @Schema(description="企业编码")
    @NotEmpty(message = "企业编码不能为空")
    @Size(max = 10, message = "企业编码长度不能超过10")
    private String tenantCode;

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

}
