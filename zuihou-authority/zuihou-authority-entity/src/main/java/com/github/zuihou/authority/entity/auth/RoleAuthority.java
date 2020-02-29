package com.github.zuihou.authority.entity.auth;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zuihou.authority.enumeration.auth.AuthorizeType;
import com.github.zuihou.base.entity.SuperEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * 实体类
 * 角色的资源
 * </p>
 *
 * @author zuihou
 * @since 2019-10-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_auth_role_authority")
@ApiModel(value = "RoleAuthority", description = "角色的资源")
public class RoleAuthority extends SuperEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 资源id
     * #c_auth_resource
     * #c_auth_menu
     */
    @ApiModelProperty(value = "资源id")
    @NotNull(message = "资源id不能为空")
    @TableField("authority_id")
    private Long authorityId;

    /**
     * 权限类型
     * #AuthorizeType{MENU:菜单;RESOURCE:资源;}
     */
    @ApiModelProperty(value = "权限类型")
    @NotNull(message = "权限类型不能为空")
    @TableField("authority_type")
    private AuthorizeType authorityType;

    /**
     * 角色id
     * #c_auth_role
     */
    @ApiModelProperty(value = "角色id")
    @NotNull(message = "角色id不能为空")
    @TableField("role_id")
    private Long roleId;


    @Builder
    public RoleAuthority(Long id, LocalDateTime createTime, Long createUser,
                         Long authorityId, AuthorizeType authorityType, Long roleId) {
        this.id = id;
        this.createTime = createTime;
        this.createUser = createUser;
        this.authorityId = authorityId;
        this.authorityType = authorityType;
        this.roleId = roleId;
    }

}
