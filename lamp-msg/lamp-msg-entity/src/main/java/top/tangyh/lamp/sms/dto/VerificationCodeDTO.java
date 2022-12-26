package top.tangyh.lamp.sms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.basic.base.entity.SuperEntity;
import top.tangyh.lamp.sms.enumeration.VerificationCodeType;

import java.io.Serializable;

/**
 * 验证码发送验证DTO
 *
 * @author zuihou
 * @date 2019/08/06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Schema(description = "验证码发送验证DTO")
public class VerificationCodeDTO implements Serializable {
    @Schema(description = "手机号")
    @NotEmpty(message = "手机号不能为空")
    private String mobile;
    @Schema(description = "类型")
    @NotNull(message = "类型不能为空")
    private VerificationCodeType type;

    @Schema(description = "验证码")
    @NotEmpty(groups = SuperEntity.Update.class, message = "验证码不能为空")
    private String code;
}
