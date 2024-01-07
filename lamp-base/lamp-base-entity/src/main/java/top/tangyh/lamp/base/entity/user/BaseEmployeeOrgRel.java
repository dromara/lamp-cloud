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
 * 员工所在部门
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
@TableName("base_employee_org_rel")
@AllArgsConstructor
public class BaseEmployeeOrgRel extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 机构ID
     */
    @TableField(value = "org_id")
    private Long orgId;

    /**
     * 员工ID
     */
    @TableField(value = "employee_id")
    private Long employeeId;

    /**
     * 创建机构ID
     */
    @TableField(value = "created_org_id")
    private Long createdOrgId;


    @Builder
    public BaseEmployeeOrgRel(Long id, Long createdBy, LocalDateTime createdTime, Long updatedBy, LocalDateTime updatedTime,
                              Long orgId, Long employeeId, Long createdOrgId) {
        this.id = id;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.updatedBy = updatedBy;
        this.updatedTime = updatedTime;
        this.orgId = orgId;
        this.employeeId = employeeId;
        this.createdOrgId = createdOrgId;
    }

}
