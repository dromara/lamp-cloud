package top.tangyh.lamp.base.vo.update.user;

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
 * 员工
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
@Schema(description = "员工")
public class BaseEmployeeUpdateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @NotNull(message = "请填写主键", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 岗位Id
     */
    @Schema(description = "岗位Id")
    private Long positionId;
    /**
     * 机构ID
     */
    @Schema(description = "所属部门")
    private List<Long> orgIdList;
    /**
     * 真实姓名
     */
    @Schema(description = "真实姓名")
    @NotEmpty(message = "请填写真实姓名")
    @Size(max = 255, message = "真实姓名长度不能超过{max}")
    private String realName;

    /**
     * 职位状态;[10-在职 20-离职]@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.POSITION_STATUS)
     */
    @Schema(description = "职位状态")
    @Size(max = 2, message = "职位状态长度不能超过{max}")
    private String positionStatus;
    /**
     * 状态;[0-禁用 1-启用]
     */
    @Schema(description = "状态")
    private Boolean state;
}
