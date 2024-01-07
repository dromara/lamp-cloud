package top.tangyh.lamp.system.vo.query.application;

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
 * 资源接口
 * </p>
 *
 * @author zuihou
 * @since 2021-09-17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description = "资源接口")
public class DefResourceApiPageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 资源ID
     */
    @Schema(description = "资源ID")
    private Long resourceId;
    /**
     * 控制器类名
     */
    @Schema(description = "控制器类名")
    private String controller;
    /**
     * 所属服务;取配置文件中 spring.application.name
     */
    @Schema(description = "所属服务")
    private String springApplicationName;
    /**
     * 请求类型
     */
    @Schema(description = "请求类型")
    private String requestMethod;
    /**
     * 接口名;接口上的注释
     */
    @Schema(description = "接口名")
    private String name;
    /**
     * 接口路径;lamp-cloud版：uri需要拼接上gateway中路径前缀
     * lamp-boot版: uri需要不需要拼接前缀
     */
    @Schema(description = "接口路径")
    private String uri;
    private Boolean isInput;

}
