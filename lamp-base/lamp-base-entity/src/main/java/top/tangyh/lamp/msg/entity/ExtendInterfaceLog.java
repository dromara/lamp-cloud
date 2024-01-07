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
 * 接口执行日志
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
@TableName("extend_interface_log")
public class ExtendInterfaceLog extends Entity<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 接口ID;
     * #extend_interface
     */
    @TableField(value = "interface_id", condition = EQUAL)
    private Long interfaceId;
    /**
     * 接口名称
     */
    @TableField(value = "name", condition = LIKE)
    private String name;
    /**
     * 成功次数
     */
    @TableField(value = "success_count", condition = EQUAL)
    private Integer successCount;
    /**
     * 失败次数
     */
    @TableField(value = "fail_count", condition = EQUAL)
    private Integer failCount;
    /**
     * 最后执行时间
     */
    @TableField(value = "last_exec_time", condition = EQUAL)
    private LocalDateTime lastExecTime;


}
