package top.tangyh.lamp.base.vo.update.system;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
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
 * 角色的资源
 * </p>
 *
 * @author zuihou
 * @since 2021-10-18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description = "角色的资源")
public class BaseRoleResourceRelUpdateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @NotNull(message = "请填写主键", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 资源id;#def_resource
     */
    @Schema(description = "资源id")
    @NotNull(message = "请填写资源id")
    private Long resourceId;
    /**
     * 角色id;#base_role
     */
    @Schema(description = "角色id")
    @NotNull(message = "请填写角色id")
    private Long roleId;
    /**
     * 组织ID
     */
    @Schema(description = "组织ID")
    private Long orgId;
}
