package top.tangyh.lamp.base.entity.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.basic.base.entity.Entity;

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
@TableName("base_operation_log_ext")
@AllArgsConstructor
public class BaseOperationLogExt extends Entity<Long> {

    private static final long serialVersionUID = 1L;


    /**
     * 请求参数
     */
    @TableField(value = "params", condition = LIKE)
    private String params;

    /**
     * 返回值
     */
    @TableField(value = "result", condition = LIKE)
    private String result;

    /**
     * 异常描述
     */
    @TableField(value = "ex_detail", condition = LIKE)
    private String exDetail;

}
