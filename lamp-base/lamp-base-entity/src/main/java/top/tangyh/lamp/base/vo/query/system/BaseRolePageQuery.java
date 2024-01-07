package top.tangyh.lamp.base.vo.query.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 角色
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
@Schema(description = "角色")
public class BaseRolePageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色类型;10-系统角色 20-自定义角色
     */
    @Schema(description = "角色类型")
    private String type;
    /**
     * 名称
     */
    @Schema(description = "名称")
    private String name;
    /**
     * 编码
     */
    @Schema(description = "编码")
    private String code;
    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remarks;
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
     * 组织ID
     */
    @Schema(description = "组织ID")
    private Long orgId;

    @Schema(description = "范围类型")
    private String scopeType;
    @Schema(description = "是否绑定范围")
    private String scope;
    @Schema(description = "员工ID")
    private Long employeeId;
}
