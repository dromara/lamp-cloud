package com.github.zuihou.authority.entity.auth;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zuihou.base.entity.SuperEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 实体类
 * 角色的资源
 * </p>
 *
 * @author zuihou
 * @since 2019-06-23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_auth_role_resource")
@ApiModel(value = "RoleResource", description = "角色的资源")
public class RoleResource extends SuperEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 资源id
     * #c_auth_resource
     */
    @ApiModelProperty(value = "资源id")
    @TableField("resource_id")
    private Long resourceId;

    /**
     * 角色id
     * #c_auth_role
     */
    @ApiModelProperty(value = "角色id")
    @TableField("role_id")
    private Long roleId;


    @Builder
    public RoleResource(Long id, LocalDateTime createTime, Long createUser,
                        Long resourceId, Long roleId) {
        this.id = id;
        this.createTime = createTime;
        this.createUser = createUser;
        this.resourceId = resourceId;
        this.roleId = roleId;
    }

}
