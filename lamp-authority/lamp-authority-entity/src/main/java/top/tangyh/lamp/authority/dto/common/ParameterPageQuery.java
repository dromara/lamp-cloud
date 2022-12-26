package top.tangyh.lamp.authority.dto.common;


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
 * 参数配置
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
@Schema(description="参数配置")
public class ParameterPageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 参数键
     */
    @Schema(description="参数键")
    private String key;
    /**
     * 参数值
     */
    @Schema(description="参数值")
    private String value;
    /**
     * 参数名称
     */
    @Schema(description="参数名称")
    private String name;
    /**
     * 描述
     */
    @Schema(description="描述")
    private String describe;
    /**
     * 状态
     */
    @Schema(description="状态")
    private Boolean state;
    /**
     * 内置
     */
    @Schema(description="内置")
    private Boolean readonly;

}
