package top.tangyh.lamp.generator.vo.save;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.lamp.generator.enumeration.FileOverrideStrategyEnum;
import top.tangyh.lamp.generator.enumeration.TemplateEnum;

import java.util.List;
import java.util.Map;

/**
 * @author zuihou
 * @date 2022/3/3 14:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description = "生成代码")
public class DefGenVO {

    @Schema(description = "需生成表")
    @NotEmpty(message = "请选择需生成表")
    private List<Long> ids;

    @Schema(description = "生成类型")
    @NotNull(message = "请选择需生成类型")
    private TemplateEnum template;
    /** 文件覆盖配置 */
    @Schema(description = "文件覆盖配置")
    private Map<String, FileOverrideStrategyEnum> fileOverrideConfig;

}
