package top.tangyh.lamp.authority.dto.core;

import top.tangyh.basic.base.entity.SuperEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 岗位
 * </p>
 *
 * @author zuihou
 * @since 2021-04-01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description="岗位")
public class StationUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description="主键")
    @NotNull(message = "id不能为空", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 名称
     */
    @Schema(description="名称")
    @NotEmpty(message = "名称不能为空")
    @Size(max = 255, message = "名称长度不能超过255")
    private String name;
    /**
     * 组织
     * #c_org
     *
     * @Echo(api = ORG_ID_CLASS,  beanClass = Org.class)
     */
    @Schema(description="组织")
    private Long orgId;
    /**
     * 状态
     */
    @Schema(description="状态")
    private Boolean state;
    /**
     * 描述
     */
    @Schema(description="描述")
    @Size(max = 255, message = "描述长度不能超过255")
    private String describe;
}
