package top.tangyh.lamp.authority.dto.auth;


import io.swagger.v3.oas.annotations.media.Schema;
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
 * 角色
 * </p>
 *
 * @author zuihou
 * @since 2019-11-11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description = "角色")
public class RoleQueryDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Schema(description = "ID")
    private Long id;

    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    private String name;
    /**
     * 角色编码
     */
    @Schema(description = "角色编码")
    private String code;
    /**
     * 描述
     */
    @Schema(description = "描述")
    private String describe;
    /**
     * 状态
     */
    @Schema(description = "状态")
    private Boolean state;
    /**
     * 内置角色
     */
    @Schema(description = "内置角色")
    private Boolean readonly;
    /**
     * 角色类别;[10-功能角色 20-桌面角色 30-数据角色]
     */
    @Schema(description = "角色类别")
    private String category;
    /**
     * 关联的组织
     */
    @Schema(description = "关联的组织")
    private List<Long> orgList;
}
