package com.tangyh.lamp.authority.entity.auth;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangyh.basic.base.entity.SuperEntity;
import com.tangyh.lamp.authority.enumeration.auth.AuthorizeType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
 * @since 2020-11-20
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_role_authority")
@ApiModel(value = "RoleAuthority", description = "角色的资源")
@AllArgsConstructor
public class RoleAuthority extends SuperEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 资源id
     * #c_resource #c_menu
     */
    @ApiModelProperty(value = "资源id")
    @NotNull(message = "资源id不能为空")
    @TableField("authority_id")
    @Excel(name = "资源id")
    private Long authorityId;

    /**
     * 权限类型
     * #AuthorizeType{MENU:菜单;RESOURCE:资源;}
     */
    @ApiModelProperty(value = "权限类型")
    @NotNull(message = "权限类型不能为空")
    @TableField("authority_type")
    @Excel(name = "权限类型", replace = {"菜单_MENU", "资源_RESOURCE", "_null"})
    private AuthorizeType authorityType;

    /**
     * 角色id
     * #c_role
     */
    @ApiModelProperty(value = "角色id")
    @NotNull(message = "角色id不能为空")
    @TableField("role_id")
    @Excel(name = "角色id")
    private Long roleId;


    @Builder
    public RoleAuthority(Long id, LocalDateTime createTime, Long createdBy,
                         Long authorityId, AuthorizeType authorityType, Long roleId) {
        this.id = id;
        this.createTime = createTime;
        this.createdBy = createdBy;
        this.authorityId = authorityId;
        this.authorityType = authorityType;
        this.roleId = roleId;
    }

}
