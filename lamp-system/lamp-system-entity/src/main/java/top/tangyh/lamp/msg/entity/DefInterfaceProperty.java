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
 * 接口属性
 * </p>
 *
 * @author zuihou
 * @date 2022-07-04 15:51:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@TableName("def_interface_property")
public class DefInterfaceProperty extends Entity<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 接口ID
     */
    @TableField(value = "interface_id", condition = EQUAL)
    private Long interfaceId;
    /**
     * 参数名称
     */
    @TableField(value = "name", condition = LIKE)
    private String name;
    /**
     * 参数键
     */
    @TableField(value = "key_", condition = LIKE)
    private String key;
    /**
     * 参数值
     */
    @TableField(value = "value", condition = LIKE)
    private String value;
    /**
     * 顺序号
     */
    @TableField(value = "sort_value", condition = EQUAL)
    private Integer sortValue;
    /**
     * 备注
     */
    @TableField(value = "remarks", condition = LIKE)
    private String remarks;


}
