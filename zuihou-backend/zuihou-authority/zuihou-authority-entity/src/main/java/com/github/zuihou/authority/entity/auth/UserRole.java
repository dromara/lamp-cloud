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
 * 角色分配
 * 账号角色绑定
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
@TableName("c_auth_user_role")
@ApiModel(value = "UserRole", description = "角色分配 账号角色绑定")
public class UserRole extends SuperEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     * #c_auth_role
     */
    @ApiModelProperty(value = "角色id")
    @TableField("role_id")
    private Long roleId;

    /**
     * 用户id
     * #c_core_account
     */
    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Long userId;


    @Builder
    public UserRole(Long id, Long createUser, LocalDateTime createTime,
                    Long roleId, Long userId) {
        this.id = id;
        this.createUser = createUser;
        this.createTime = createTime;
        this.roleId = roleId;
        this.userId = userId;
    }

}
