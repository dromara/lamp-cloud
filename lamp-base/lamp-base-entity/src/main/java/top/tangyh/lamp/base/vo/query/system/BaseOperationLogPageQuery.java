package top.tangyh.lamp.base.vo.query.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.lamp.base.enumeration.system.LogType;
import top.tangyh.lamp.model.enumeration.HttpMethod;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 实体类
 * 操作日志
 * </p>
 *
 * @author zuihou
 * @since 2021-11-08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description = "操作日志")
public class BaseOperationLogPageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 操作IP
     */
    @Schema(description = "操作IP")
    private String requestIp;
    /**
     * 日志类型;#LogType{OPT:操作类型;EX:异常类型}
     */
    @Schema(description = "日志类型")
    private LogType type;
    /**
     * 操作人
     */
    @Schema(description = "操作人")
    private String userName;
    /**
     * 操作描述
     */
    @Schema(description = "操作描述")
    private String description;
    /**
     * 类路径
     */
    @Schema(description = "类路径")
    private String classPath;
    /**
     * 请求方法
     */
    @Schema(description = "请求方法")
    private String actionMethod;
    /**
     * 请求地址
     */
    @Schema(description = "请求地址")
    private String requestUri;
    /**
     * 请求类型;#HttpMethod{GET:GET请求;POST:POST请求;PUT:PUT请求;DELETE:DELETE请求;PATCH:PATCH请求;TRACE:TRACE请求;HEAD:HEAD请求;OPTIONS:OPTIONS请求;}
     */
    @Schema(description = "请求类型")
    private HttpMethod httpMethod;
    /**
     * 开始时间
     */
    @Schema(description = "开始时间")
    private LocalDateTime startTime;
    /**
     * 完成时间
     */
    @Schema(description = "完成时间")
    private LocalDateTime finishTime;
    /**
     * 消耗时间
     */
    @Schema(description = "消耗时间")
    private Long consumingTime;
    /**
     * 浏览器
     */
    @Schema(description = "浏览器")
    private String ua;
    /**
     * 创建人组织
     */
    @Schema(description = "创建人组织")
    private Long createdOrgId;

}
