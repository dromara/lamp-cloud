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

import static com.baomidou.mybatisplus.annotation.SqlCondition.EQUAL;
import static top.tangyh.lamp.model.constant.Condition.LIKE;


/**
 * <p>
 * 实体类
 * 接口
 * </p>
 *
 * @author zuihou
 * @date 2022-07-04 16:45:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@TableName("def_interface")
public class DefInterface extends Entity<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 接口编码
     */
    @TableField(value = "code", condition = LIKE)
    private String code;
    /**
     * 接口名称
     */
    @TableField(value = "name", condition = LIKE)
    private String name;
    /**
     * 执行方式;
     *
     * @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.INTERFACE_EXEC_MODE)
     * [01-实现类 02-脚本]
     */
    @TableField(value = "exec_mode", condition = LIKE)
    private String execMode;
    /**
     * 实现脚本
     */
    @TableField(value = "script", condition = LIKE)
    private String script;
    /**
     * 实现类
     */
    @TableField(value = "impl_class", condition = LIKE)
    private String implClass;
    /**
     * 状态
     */
    @TableField(value = "state", condition = EQUAL)
    private Boolean state;


}
