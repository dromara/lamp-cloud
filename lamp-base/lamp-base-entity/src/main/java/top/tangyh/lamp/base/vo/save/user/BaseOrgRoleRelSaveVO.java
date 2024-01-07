package top.tangyh.lamp.base.vo.save.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 实体类
 * 机构的角色
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
@Schema(description = "机构的角色")
public class BaseOrgRoleRelSaveVO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Schema(description = "绑定或取消")
    @NotNull(message = "请填写绑定或取消参数")
    private Boolean flag;
    /**
     * 角色;#base_role
     */
    @Schema(description = "角色")
    @Size(min = 1, message = "请选择角色")
    private List<Long> roleIdList;
    /**
     * 机构;#base_org
     */
    @Schema(description = "机构")
    @NotNull(message = "请选择机构")
    private Long orgId;

}
