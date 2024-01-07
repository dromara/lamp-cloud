package top.tangyh.lamp.base.entity.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.basic.base.entity.Entity;
import top.tangyh.lamp.base.enumeration.system.LogType;
import top.tangyh.lamp.model.enumeration.HttpMethod;

import java.time.LocalDateTime;

import static top.tangyh.lamp.model.constant.Condition.LIKE;

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
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("base_operation_log")
@AllArgsConstructor
public class BaseOperationLog extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 操作IP
     */
    @TableField(value = "request_ip", condition = LIKE)
    private String requestIp;

    /**
     * 日志类型;#LogType{OPT:操作类型;EX:异常类型}
     */
    @TableField(value = "type")
    private LogType type;

    /**
     * 操作人
     */
    @TableField(value = "user_name", condition = LIKE)
    private String userName;

    /**
     * 操作描述
     */
    @TableField(value = "description", condition = LIKE)
    private String description;

    /**
     * 类路径
     */
    @TableField(value = "class_path", condition = LIKE)
    private String classPath;

    /**
     * 请求方法
     */
    @TableField(value = "action_method", condition = LIKE)
    private String actionMethod;

    /**
     * 请求地址
     */
    @TableField(value = "request_uri", condition = LIKE)
    private String requestUri;

    /**
     * 请求类型;#HttpMethod{GET:GET请求;POST:POST请求;PUT:PUT请求;DELETE:DELETE请求;PATCH:PATCH请求;TRACE:TRACE请求;HEAD:HEAD请求;OPTIONS:OPTIONS请求;}
     */
    @TableField(value = "http_method")
    private HttpMethod httpMethod;

    /**
     * 开始时间
     */
    @TableField(value = "start_time")
    private LocalDateTime startTime;

    /**
     * 完成时间
     */
    @TableField(value = "finish_time")
    private LocalDateTime finishTime;

    /**
     * 消耗时间
     */
    @TableField(value = "consuming_time")
    private Long consumingTime;

    /**
     * 浏览器
     */
    @TableField(value = "ua", condition = LIKE)
    private String ua;

    /**
     * 创建人组织
     */
    @TableField(value = "created_org_id")
    private Long createdOrgId;


    @Builder
    public BaseOperationLog(Long id, LocalDateTime createdTime, Long createdBy, LocalDateTime updatedTime, Long updatedBy,
                            String requestIp, LogType type, String userName, String description, String classPath,
                            String actionMethod, String requestUri, HttpMethod httpMethod, LocalDateTime startTime, LocalDateTime finishTime, Long consumingTime,
                            String ua, Long createdOrgId) {
        this.id = id;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
        this.updatedTime = updatedTime;
        this.updatedBy = updatedBy;
        this.requestIp = requestIp;
        this.type = type;
        this.userName = userName;
        this.description = description;
        this.classPath = classPath;
        this.actionMethod = actionMethod;
        this.requestUri = requestUri;
        this.httpMethod = httpMethod;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.consumingTime = consumingTime;
        this.ua = ua;
        this.createdOrgId = createdOrgId;
    }

}
