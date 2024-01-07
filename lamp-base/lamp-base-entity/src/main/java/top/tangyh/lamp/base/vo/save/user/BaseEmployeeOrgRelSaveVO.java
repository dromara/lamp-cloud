package top.tangyh.lamp.base.vo.save.user;

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

/**
 * <p>
 * 实体类
 * 员工所在部门
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
@Schema(description = "员工所在部门")
public class BaseEmployeeOrgRelSaveVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 机构ID
     */
    @Schema(description = "机构ID")
    @NotNull(message = "请填写机构ID")
    private Long orgId;
    /**
     * 员工ID
     */
    @Schema(description = "员工ID")
    @NotNull(message = "请填写员工ID")
    private Long employeeId;

}
