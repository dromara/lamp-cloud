package top.tangyh.lamp.authority.dto.auth;


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
import java.util.List;

/**
 * <p>
 * 实体类
 * 角色
 * </p>
 *
 * @author zuihou
 * @since 2020-11-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description = "角色")
public class RoleUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @NotNull(message = "id不能为空", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 名称
     */
    @Schema(description = "名称")
    @NotEmpty(message = "名称不能为空")
    @Size(max = 30, message = "名称长度不能超过30")
    private String name;
    /**
     * 编码
     */
    @Schema(description = "编码")
    @Size(max = 20, message = "编码长度不能超过20")
    private String code;
    /**
     * 描述
     */
    @Schema(description = "描述")
    @Size(max = 100, message = "描述长度不能超过100")
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
    @Size(max = 2, message = "角色类别长度不能超过{max}")
    private String category;
    /**
     * 关联的组织
     */
    @Schema(description = "关联的组织")
    private List<Long> orgList;

}
