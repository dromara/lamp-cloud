package top.tangyh.lamp.msg.vo.update;

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

/**
 * <p>
 * 表单修改方法VO
 * 接口属性
 * </p>
 *
 * @author zuihou
 * @date 2022-07-04 15:51:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(description = "接口属性")
public class DefInterfacePropertyUpdateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    @NotNull(message = "请填写ID", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 接口ID
     */
    @Schema(description = "接口ID")
    @NotNull(message = "请填写接口ID")
    private Long interfaceId;
    /**
     * 参数名称
     */
    @Schema(description = "参数名称")
    @NotEmpty(message = "请填写参数名称")
    @Size(max = 255, message = "参数名称长度不能超过{max}")
    private String name;
    /**
     * 参数键
     */
    @Schema(description = "参数键")
    @NotEmpty(message = "请填写参数键")
    @Size(max = 255, message = "参数键长度不能超过{max}")
    private String key;
    /**
     * 参数值
     */
    @Schema(description = "参数值")
    @NotEmpty(message = "请填写参数值")
    @Size(max = 255, message = "参数值长度不能超过{max}")
    private String value;
    /**
     * 顺序号
     */
    @Schema(description = "顺序号")
    private Integer sortValue;
    /**
     * 备注
     */
    @Schema(description = "备注")
    @Size(max = 255, message = "备注长度不能超过{max}")
    private String remarks;


}
