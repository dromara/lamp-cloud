package top.tangyh.lamp.base.vo.save.system;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

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
public class BaseRoleResourceRelSaveVO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 角色id;#base_role
     */
    @Schema(description = "角色id")
    @NotNull(message = "请填写角色id")
    private Long roleId;
    /**
     * 编码
     */
    @Schema(description = "应用-资源 列表 [必填]")
    @NotNull
    private Map<Long, List<Long>> applicationResourceMap;
}
