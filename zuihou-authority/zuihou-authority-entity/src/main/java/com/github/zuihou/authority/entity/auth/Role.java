package com.github.zuihou.authority.entity.auth;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zuihou.base.entity.Entity;
import com.github.zuihou.database.mybatis.auth.DataScopeType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

/**
 * <p>
 * 实体类
 * 角色
 * </p>
 *
 * @author zuihou
 * @since 2019-11-11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_auth_role")
@ApiModel(value = "Role", description = "角色")
public class Role extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    @NotEmpty(message = "角色名称不能为空")
    @Length(max = 30, message = "角色名称长度不能超过30")
    @TableField(value = "name", condition = LIKE)
    @Excel(name = "角色名称", width = 40)
    private String name;

    /**
     * 角色编码
     */
    @ApiModelProperty(value = "角色编码")
    @Length(max = 20, message = "角色编码长度不能超过20")
    @TableField(value = "code", condition = LIKE)
    @Excel(name = "角色编码", width = 30)
    private String code;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @Length(max = 100, message = "描述长度不能超过100")
    @TableField(value = "describe_", condition = LIKE)
    @Excel(name = "描述", width = 50)
    private String describe;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField("status")
    @Excel(name = "状态", replace = {"启用_true", "禁用_false", "_null"})
    private Boolean status;

    /**
     * 是否内置角色
     */
    @ApiModelProperty(value = "是否内置角色")
    @TableField("readonly")
    @Excel(name = "是否内置角色", replace = {"是_true", "否_false", "_null"})
    private Boolean readonly;

    /**
     * 数据权限类型
     * #DataScopeType{ALL:5,全部;THIS_LEVEL:4,本级;THIS_LEVEL_CHILDREN:3,本级以及子级;CUSTOMIZE:2,自定义;SELF:1,个人;}
     */
    @ApiModelProperty(value = "数据权限类型")
    @NotNull(message = "数据权限类型不能为空")
    @TableField("ds_type")
    @Excel(name = "数据权限类型", replace = {"全部_ALL", "本级_THIS_LEVEL", "本级以及子级_THIS_LEVEL_CHILDREN", "自定义_CUSTOMIZE", "个人_SELF", "_null"})
    private DataScopeType dsType;


    @Builder
    public Role(Long id, Long createUser, LocalDateTime createTime, Long updateUser, LocalDateTime updateTime,
                String name, String code, String describe, Boolean status, Boolean readonly, DataScopeType dsType) {
        this.id = id;
        this.createUser = createUser;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
        this.name = name;
        this.code = code;
        this.describe = describe;
        this.status = status;
        this.readonly = readonly;
        this.dsType = dsType;
    }

}
