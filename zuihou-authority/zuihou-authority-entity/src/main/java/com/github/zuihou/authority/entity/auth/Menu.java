package com.github.zuihou.authority.entity.auth;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zuihou.base.entity.TreeEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

/**
 * <p>
 * 实体类
 * 菜单
 * </p>
 *
 * @author zuihou
 * @since 2019-11-09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_auth_menu")
@ApiModel(value = "Menu", description = "菜单")
public class Menu extends TreeEntity<Menu, Long> {

    private static final long serialVersionUID = 1L;

    @Excel(name = "名称", width = 20)
    protected String label;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @Length(max = 200, message = "描述长度不能超过200")
    @TableField(value = "describe_", condition = LIKE)
    @Excel(name = "描述", width = 50)
    private String describe;

    /**
     * 是否公开菜单
     * 就是无需分配就可以访问的。所有人可见
     */
    @ApiModelProperty(value = "公共菜单")
    @TableField("is_public")
    @Excel(name = "公共菜单", replace = {"是_true", "否_false", "_null"})
    private Boolean isPublic;

    /**
     * 路径
     */
    @ApiModelProperty(value = "路径")
    @Length(max = 255, message = "路径长度不能超过255")
    @TableField(value = "path", condition = LIKE)
    @Excel(name = "路径", width = 20)
    private String path;

    /**
     * 组件
     */
    @ApiModelProperty(value = "组件")
    @Length(max = 255, message = "组件长度不能超过255")
    @TableField(value = "component", condition = LIKE)
    @Excel(name = "组件", width = 30)
    private String component;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField("is_enable")
    @Excel(name = "状态", replace = {"启用_true", "禁用_false", "_null"})
    private Boolean isEnable;

    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标")
    @Length(max = 255, message = "菜单图标长度不能超过255")
    @TableField(value = "icon", condition = LIKE)
    private String icon;

    /**
     * 分组
     */
    @ApiModelProperty(value = "分组")
    @Length(max = 20, message = "分组长度不能超过20")
    @TableField(value = "group_", condition = LIKE)
    @Excel(name = "分组")
    private String group;

    @Builder
    public Menu(Long id, Long createUser, LocalDateTime createTime, Long updateUser, LocalDateTime updateTime,
                String label, String describe, Boolean isPublic, String path, String component,
                Boolean isEnable, Integer sortValue, String icon, String group, Long parentId) {
        this.id = id;
        this.createUser = createUser;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
        this.label = label;
        this.describe = describe;
        this.isPublic = isPublic;
        this.path = path;
        this.component = component;
        this.isEnable = isEnable;
        this.sortValue = sortValue;
        this.icon = icon;
        this.group = group;
        this.parentId = parentId;
    }

}
