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
 * 岗位
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
@TableName("base_position")
@AllArgsConstructor
public class BasePosition extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @TableField(value = "name", condition = LIKE)
    private String name;

    /**
     * 所属组织;#base_org@Echo(api = EchoApi.ORG_ID_CLASS)
     */
    @TableField(value = "org_id")
    private Long orgId;

    /**
     * 状态;0-禁用 1-启用
     */
    @TableField(value = "state")
    private Boolean state;

    /**
     * 备注
     */
    @TableField(value = "remarks", condition = LIKE)
    private String remarks;

    /**
     * 创建机构Id
     */
    @TableField(value = "created_org_id")
    private Long createdOrgId;


    @Builder
    public BasePosition(Long id, LocalDateTime createdTime, Long createdBy, LocalDateTime updatedTime, Long updatedBy,
                        String name, Long orgId, Boolean state, String remarks, Long createdOrgId) {
        this.id = id;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
        this.updatedTime = updatedTime;
        this.updatedBy = updatedBy;
        this.name = name;
        this.orgId = orgId;
        this.state = state;
        this.remarks = remarks;
        this.createdOrgId = createdOrgId;
    }

}
