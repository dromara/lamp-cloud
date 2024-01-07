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

/**
 * <p>
 * 实体类
 * 角色的资源
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
@TableName("base_role_resource_rel")
@AllArgsConstructor
public class BaseRoleResourceRel extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 资源id;#def_resource
     */
    @TableField(value = "resource_id")
    private Long resourceId;

    /**
     * 应用id;#def_application
     */
    @TableField(value = "application_id")
    private Long applicationId;

    /**
     * 角色id;#base_role
     */
    @TableField(value = "role_id")
    private Long roleId;

    /**
     * 组织ID
     */
    @TableField(value = "created_org_id")
    private Long createdOrgId;


    @Builder
    public BaseRoleResourceRel(Long id, LocalDateTime createdTime, Long createdBy, LocalDateTime updatedTime, Long updatedBy,
                               Long resourceId, Long applicationId, Long roleId, Long createdOrgId) {
        this.id = id;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
        this.updatedTime = updatedTime;
        this.updatedBy = updatedBy;
        this.resourceId = resourceId;
        this.applicationId = applicationId;
        this.roleId = roleId;
        this.createdOrgId = createdOrgId;
    }

}
