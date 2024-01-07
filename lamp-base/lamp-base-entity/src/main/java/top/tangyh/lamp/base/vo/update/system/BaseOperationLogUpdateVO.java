package top.tangyh.lamp.base.vo.update.system;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class BaseOperationLogUpdateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @NotNull(message = "请填写主键", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 操作IP
     */
    @Schema(description = "操作IP")
    @Size(max = 50, message = "操作IP长度不能超过{max}")
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
    @Size(max = 50, message = "操作人长度不能超过{max}")
    private String userName;
    /**
     * 操作描述
     */
    @Schema(description = "操作描述")
    @Size(max = 255, message = "操作描述长度不能超过{max}")
    private String description;
    /**
     * 类路径
     */
    @Schema(description = "类路径")
    @Size(max = 255, message = "类路径长度不能超过{max}")
    private String classPath;
    /**
     * 请求方法
     */
    @Schema(description = "请求方法")
    @Size(max = 50, message = "请求方法长度不能超过{max}")
    private String actionMethod;
    /**
     * 请求地址
     */
    @Schema(description = "请求地址")
    @Size(max = 50, message = "请求地址长度不能超过{max}")
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
    @Size(max = 500, message = "浏览器长度不能超过{max}")
    private String ua;
    /**
     * 创建人组织
     */
    @Schema(description = "创建人组织")
    private Long createdOrgId;
}
