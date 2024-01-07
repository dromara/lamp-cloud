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

import java.time.LocalDateTime;

import static top.tangyh.lamp.model.constant.Condition.LIKE;

/**
 * <p>
 * 实体类
 * 角色
 * </p>
 *
 * @author zuihou
 * @since 2021-10-21
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("base_role")
@AllArgsConstructor
public class BaseRole extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色类型;10-系统角色 20-自定义角色
     */
    @TableField(value = "type_", condition = LIKE)
    private String type;
    /**
     * 角色类别;[10-功能角色 20-桌面角色 30-数据角色]
     */
    @TableField(value = "category", condition = LIKE)
    private String category;

    /**
     * 名称
     */
    @TableField(value = "name", condition = LIKE)
    private String name;

    /**
     * 编码
     */
    @TableField(value = "code", condition = LIKE)
    private String code;

    /**
     * 备注
     */
    @TableField(value = "remarks", condition = LIKE)
    private String remarks;

    /**
     * 状态
     */
    @TableField(value = "state")
    private Boolean state;

    /**
     * 内置角色
     */
    @TableField(value = "readonly_")
    private Boolean readonly;

    /**
     * 组织ID
     */
    @TableField(value = "created_org_id")
    private Long createdOrgId;


    @Builder
    public BaseRole(Long id, Long createdBy, LocalDateTime createdTime, Long updatedBy, LocalDateTime updatedTime,
                    String type, String name, String code, String remarks, Boolean state, String category,
                    Boolean readonly, Long createdOrgId) {
        this.id = id;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.updatedBy = updatedBy;
        this.updatedTime = updatedTime;
        this.type = type;
        this.name = name;
        this.code = code;
        this.remarks = remarks;
        this.state = state;
        this.category = category;
        this.readonly = readonly;
        this.createdOrgId = createdOrgId;
    }

}
