package top.tangyh.lamp.msg.vo.save;

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
 * 表单保存方法VO
 * 接口
 * </p>
 *
 * @author zuihou
 * @date 2022-07-04 16:45:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(description = "接口")
public class DefInterfaceSaveVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 接口编码
     */
    @Schema(description = "接口编码")
    @Size(max = 255, message = "接口编码长度不能超过{max}")
    @NotEmpty(message = "请填写接口编码")
    private String code;
    /**
     * 接口名称
     */
    @Schema(description = "接口名称")
    @Size(max = 255, message = "接口名称长度不能超过{max}")
    @NotEmpty(message = "请填写接口名称")
    private String name;
    /**
     * 执行方式;
     *
     * @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.INTERFACE_EXEC_MODE)
     * [01-实现类 02-脚本]
     */
    @Schema(description = "执行方式")
    @Size(max = 2, message = "执行方式长度不能超过{max}")
    private String execMode;
    /**
     * 实现脚本/实现类
     */
    @Schema(description = "实现脚本")
    @Size(max = 65535, message = "实现脚本长度不能超过{max}")
    private String script;

    @Schema(description = "实现类")
    @Size(max = 255, message = "实现类长度不能超过{max}")
    private String implClass;
    /**
     * 状态
     */
    @Schema(description = "状态")
    private Boolean state;


}
