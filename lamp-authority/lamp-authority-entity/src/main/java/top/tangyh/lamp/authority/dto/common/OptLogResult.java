package top.tangyh.lamp.authority.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.lamp.authority.entity.common.OptLog;

import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 系统日志扩展
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
@Schema(description = "系统日志扩展")
public class OptLogResult extends OptLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 请求参数
     */
    @Schema(description = "请求参数")
    private String params;
    /**
     * 返回值
     */
    @Schema(description = "返回值")
    private String result;
    /**
     * 异常描述
     */
    @Schema(description = "异常描述")
    private String exDetail;

}
