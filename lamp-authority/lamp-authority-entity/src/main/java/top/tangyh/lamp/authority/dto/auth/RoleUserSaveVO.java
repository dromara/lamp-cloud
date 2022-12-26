package top.tangyh.lamp.authority.dto.auth;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 实体类
 * 角色下的员工
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
@Schema(description="给角色分配员工")
public class RoleUserSaveVO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Schema(description="绑定或取消")
    @NotNull(message = "请填写绑定或取消参数")
    private Boolean flag;
    /**
     * 角色;#c_role
     */
    @Schema(description="角色")
    @NotNull(message = "请选择角色")
    private Long roleId;
    /**
     * 用户;#c_user
     */
    @Schema(description="用户")
    @Size(min = 1, message = "请选择用户")
    private List<Long> userIdList;

}
