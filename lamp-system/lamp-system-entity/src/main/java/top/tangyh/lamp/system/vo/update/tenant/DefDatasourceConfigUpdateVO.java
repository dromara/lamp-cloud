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
import top.tangyh.basic.base.entity.SuperEntity;

import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 数据源
 * </p>
 *
 * @author zuihou
 * @since 2021-09-15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description = "数据源")
public class DefDatasourceConfigUpdateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @NotNull(message = "请填写主键", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 名称
     */
    @Schema(description = "名称")
    @NotEmpty(message = "请填写名称")
    @Size(max = 255, message = "名称长度不能超过255")
    private String name;


    /**
     * 用户名
     */
    @Schema(description = "用户名")
    @NotEmpty(message = "请填写用户名")
    @Size(max = 255, message = "用户名长度不能超过255")
    private String username;
    /**
     * 密码
     */
    @Schema(description = "密码")
    @NotEmpty(message = "请填写密码")
    @Size(max = 255, message = "密码长度不能超过255")
    private String password;
    /**
     * 链接
     */
    @Schema(description = "链接")
    @NotEmpty(message = "请填写链接")
    @Size(max = 255, message = "链接长度不能超过255")
    private String url;
    /**
     * 驱动
     */
    @Schema(description = "驱动")
    @NotEmpty(message = "请填写驱动")
    @Size(max = 255, message = "驱动长度不能超过255")
    private String driverClassName;
}
