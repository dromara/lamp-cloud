package top.tangyh.lamp.authority.entity.auth;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.basic.base.entity.Entity;

import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

/**
 * <p>
 * 实体类
 * 数据权限
 * </p>
 *
 * @author zuihou
 * @since 2022-01-10
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@AllArgsConstructor
public class ResourceDataScope extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 权限名称
     */
    @TableField(value = "`name`", condition = LIKE)
    private String name;

    @TableField(value = "`data_scope`", condition = LIKE)
    private String dataScope;

    /**
     * 优先级;值越小，越优先
     */
    @TableField(value = "`sort_value`")
    private Integer sortValue;

    /**
     * 所属资源
     */
    @TableField(value = "`parent_id`")
    private Long parentId;

    /**
     * 实现类;自定义实现类全类名
     */
    @TableField(value = "`custom_class`", condition = LIKE)
    private String customClass;

    /**
     * 是否默认
     */
    @TableField(value = "`is_def`", condition = LIKE)
    private Boolean isDef;


    @Builder
    public ResourceDataScope(Long id, Long createdBy, LocalDateTime createTime, Long updatedBy, LocalDateTime updateTime,
                             String name, String dataScope, Integer sortValue, Long parentId, String customClass, Boolean isDef) {
        this.id = id;
        this.createdBy = createdBy;
        this.createTime = createTime;
        this.updatedBy = updatedBy;
        this.updateTime = updateTime;
        this.name = name;
        this.dataScope = dataScope;
        this.sortValue = sortValue;
        this.parentId = parentId;
        this.customClass = customClass;
        this.isDef = isDef;
    }

}
