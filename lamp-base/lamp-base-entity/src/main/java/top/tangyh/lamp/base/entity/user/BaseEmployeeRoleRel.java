package top.tangyh.lamp.base.entity.user;

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
 * 员工的角色
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
@TableName("base_employee_role_rel")
@AllArgsConstructor
public class BaseEmployeeRoleRel extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色;#base_role
     */
    @TableField(value = "role_id")
    private Long roleId;

    /**
     * 员工;#base_employee
     */
    @TableField(value = "employee_id")
    private Long employeeId;

    /**
     * 创建机构id
     */
    @TableField(value = "created_org_id")
    private Long createdOrgId;


    @Builder
    public BaseEmployeeRoleRel(Long id, Long createdBy, LocalDateTime createdTime, Long updatedBy, LocalDateTime updatedTime,
                               Long roleId, Long employeeId, Long createdOrgId) {
        this.id = id;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.updatedBy = updatedBy;
        this.updatedTime = updatedTime;
        this.roleId = roleId;
        this.employeeId = employeeId;
        this.createdOrgId = createdOrgId;
    }

}
