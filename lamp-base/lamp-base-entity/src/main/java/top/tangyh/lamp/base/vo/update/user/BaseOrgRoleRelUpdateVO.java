package top.tangyh.lamp.base.vo.update.user;

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
 * 组织的角色
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
@Schema(description = "组织的角色")
public class BaseOrgRoleRelUpdateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @NotNull(message = "请填写主键", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 部门;#base_org
     */
    @Schema(description = "部门")
    @NotNull(message = "请填写部门")
    private Long orgId;
    /**
     * 角色;#base_role
     */
    @Schema(description = "角色")
    @NotNull(message = "请填写角色")
    private Long roleId;
}
