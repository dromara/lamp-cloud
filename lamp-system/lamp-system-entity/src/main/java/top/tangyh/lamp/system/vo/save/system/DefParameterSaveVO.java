package top.tangyh.lamp.system.vo.save.system;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
 * 参数配置
 * </p>
 *
 * @author zuihou
 * @since 2021-10-13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description = "参数配置")
public class DefParameterSaveVO implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 参数名称
     */
    @Schema(description = "参数名称")
    @NotEmpty(message = "请填写参数名称")
    @Size(max = 255, message = "参数名称长度不能超过{max}")
    private String name;
    /**
     * 备注
     */
    @Schema(description = "备注")
    @Size(max = 255, message = "备注长度不能超过{max}")
    private String remarks;
    /**
     * 状态
     */
    @Schema(description = "状态")
    private Boolean state;
    /**
     * 类型;[10-系统参数 20-业务参数]@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.System.PARAMETER_TYPE)
     */
    @Schema(description = "类型")
    @Size(max = 2, message = "类型长度不能超过{max}")
    private String paramType;

}
