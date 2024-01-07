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

import static top.tangyh.lamp.model.constant.Condition.LIKE;

/**
 * <p>
 * 实体类
 * 员工
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
@TableName("base_employee")
@AllArgsConstructor
public class BaseEmployee extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 是否默认员工;[0-否 1-是]
     */
    @TableField(value = "is_default")
    private Boolean isDefault;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;
    /**
     * 上一次登录单位ID
     */
    @TableField(value = "last_company_id")
    private Long lastCompanyId;
    /**
     * 上一次登录部门ID
     */
    @TableField(value = "last_dept_id")
    private Long lastDeptId;

    /**
     * 岗位Id
     */
    @TableField(value = "position_id")
    private Long positionId;

    /**
     * 真实姓名
     */
    @TableField(value = "real_name", condition = LIKE)
    private String realName;


    /**
     * 职位状态;[10-在职 20-离职]@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.POSITION_STATUS)
     */
    @TableField(value = "position_status", condition = LIKE)
    private String positionStatus;
    /**
     * 激活状态;[10-未激活 20-已激活]
     */
    @TableField(value = "active_status", condition = LIKE)
    private String activeStatus;

    /**
     * 状态;[0-禁用 1-启用]
     */
    @TableField(value = "state")
    private Boolean state;

    /**
     * 创建机构Id
     */
    @TableField(value = "created_org_id")
    private Long createdOrgId;


    @Builder
    public BaseEmployee(Long id, Long createdBy, LocalDateTime createdTime, Long updatedBy, LocalDateTime updatedTime,
                        Boolean isDefault, Long userId, Long positionId, String realName, Long lastCompanyId, Long lastDeptId,
                        String positionStatus, String activeStatus, Boolean state, Long createdOrgId) {
        this.id = id;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.updatedBy = updatedBy;
        this.updatedTime = updatedTime;
        this.isDefault = isDefault;
        this.userId = userId;
        this.positionId = positionId;
        this.realName = realName;
        this.positionStatus = positionStatus;
        this.activeStatus = activeStatus;
        this.state = state;
        this.createdOrgId = createdOrgId;
        this.lastDeptId = lastDeptId;
        this.lastCompanyId = lastCompanyId;
    }

}
