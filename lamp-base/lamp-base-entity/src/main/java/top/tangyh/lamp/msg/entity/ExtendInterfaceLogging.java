package top.tangyh.lamp.msg.entity;

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

import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.SqlCondition.EQUAL;
import static top.tangyh.lamp.model.constant.Condition.LIKE;


/**
 * <p>
 * 实体类
 * 接口执行日志记录
 * </p>
 *
 * @author zuihou
 * @date 2022-07-09 23:58:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@TableName("extend_interface_logging")
public class ExtendInterfaceLogging extends Entity<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 接口日志ID;
     * #extend_interface_log
     */
    @TableField(value = "log_id", condition = EQUAL)
    private Long logId;
    /**
     * 执行时间
     */
    @TableField(value = "exec_time", condition = EQUAL)
    private LocalDateTime execTime;
    /**
     * 执行状态;
     * [01-初始化 02-成功 03-失败]
     *
     * @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.MSG_INTERFACE_LOGGING_STATUS)
     */
    @TableField(value = "status", condition = LIKE)
    private String status;
    /**
     * 请求参数
     */
    @TableField(value = "params", condition = LIKE)
    private String params;
    /**
     * 接口返回
     */
    @TableField(value = "result", condition = LIKE)
    private String result;
    /**
     * 异常消息
     */
    @TableField(value = "error_msg", condition = LIKE)
    private String errorMsg;
    /**
     * 业务ID
     */
    @TableField(value = "biz_id", condition = EQUAL)
    private Long bizId;


}
