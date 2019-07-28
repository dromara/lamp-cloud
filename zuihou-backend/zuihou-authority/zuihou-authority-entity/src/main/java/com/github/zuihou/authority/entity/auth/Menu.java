package com.github.zuihou.authority.entity.auth;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zuihou.authority.enumeration.auth.TargetType;
import com.github.zuihou.base.entity.Entity;

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
 * 菜单
 * </p>
 *
 * @author zuihou
 * @since 2019-07-28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_auth_menu")
@ApiModel(value = "Menu", description = "菜单")
public class Menu extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    @NotEmpty(message = "菜单名称不能为空")
    @Length(max = 20, message = "菜单名称长度不能超过20")
    @TableField("name")
    private String name;

    /**
     * 功能描述
     */
    @ApiModelProperty(value = "功能描述")
    @Length(max = 200, message = "功能描述长度不能超过200")
    @TableField("describe_")
    private String describe;

    /**
     * 资源编码
     */
    @ApiModelProperty(value = "资源编码")
    @Length(max = 255, message = "资源编码长度不能超过255")
    @TableField("code")
    private String code;

    /**
     * 是否公开菜单
     * 就是无需分配就可以访问的。所有人可见
     */
    @ApiModelProperty(value = "是否公开菜单")
    @TableField("is_public")
    private Boolean isPublic;

    /**
     * 资源路径
     */
    @ApiModelProperty(value = "资源路径")
    @Length(max = 255, message = "资源路径长度不能超过255")
    @TableField("href")
    private String href;

    /**
     * 打开方式
     * #TargetType{SELF:_self,相同框架;TOP:_top,当前页;BLANK:_blank,新建窗口;PAREN:_parent,父窗口}
     */
    @ApiModelProperty(value = "打开方式")
    @TableField("target")
    private TargetType target;

    /**
     * 是否启用
     */
    @ApiModelProperty(value = "是否启用")
    @TableField("is_enable")
    private Boolean isEnable;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    @TableField("sort_value")
    private Integer sortValue;

    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标")
    @Length(max = 255, message = "菜单图标长度不能超过255")
    @TableField("icon")
    private String icon;

    /**
     * 菜单分组
     */
    @ApiModelProperty(value = "菜单分组")
    @Length(max = 20, message = "菜单分组长度不能超过20")
    @TableField("group_")
    private String group;

    /**
     * 父级菜单id
     */
    @ApiModelProperty(value = "父级菜单id")
    @TableField("parent_id")
    private Long parentId;


    @Builder
    public Menu(Long id, Long createUser, LocalDateTime createTime, Long updateUser, LocalDateTime updateTime,
                String name, String describe, String code, Boolean isPublic, String href,
                TargetType target, Boolean isEnable, Integer sortValue, String icon, String group, Long parentId) {
        this.id = id;
        this.createUser = createUser;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
        this.name = name;
        this.describe = describe;
        this.code = code;
        this.isPublic = isPublic;
        this.href = href;
        this.target = target;
        this.isEnable = isEnable;
        this.sortValue = sortValue;
        this.icon = icon;
        this.group = group;
        this.parentId = parentId;
    }

}
