package com.github.zuihou.authority.dto.auth;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.github.zuihou.database.mybatis.auth.DataScopeType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

/**
 * <p>
 * 实体类
 * 角色
 * </p>
 *
 * @author zuihou
 * @since 2019-07-27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "RoleSaveDTO", description = "角色")
public class RoleSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    @NotEmpty(message = "角色名称不能为空")
    @Length(max = 30, message = "角色名称长度不能超过30")
    private String name;
    /**
     * 角色编码
     */
    @ApiModelProperty(value = "角色编码")
    @Length(max = 20, message = "角色编码长度不能超过20")
    private String code;
    /**
     * 功能描述
     */
    @ApiModelProperty(value = "功能描述")
    @Length(max = 100, message = "功能描述长度不能超过100")
    private String describe;
    /**
     * 是否启用
     */
    @ApiModelProperty(value = "是否启用")
    private Boolean isEnable;

    /**
     * 数据权限类型
     * #DataScopeType{ALL:1,全部;THIS_LEVEL:2,本级;THIS_LEVEL_CHILDREN:3,本级以及子级;CUSTOMIZE:4,自定义;SELF:5,个人;}
     */
    @ApiModelProperty(value = "数据权限类型")
    @NotNull(message = "数据权限类型不能为空")
    private DataScopeType dsType;
    /**
     * 关联的组织id
     */
    @ApiModelProperty(value = "关联的组织id")
    private List<Long> orgList;
}
