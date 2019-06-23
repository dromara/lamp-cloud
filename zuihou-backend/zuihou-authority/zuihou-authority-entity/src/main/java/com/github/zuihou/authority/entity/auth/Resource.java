package com.github.zuihou.authority.entity.auth;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zuihou.authority.enumeration.auth.ResourceType;
import com.github.zuihou.authority.enumeration.auth.UrlOpenMethod;
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
 * 资源
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
@TableName("c_auth_resource")
@ApiModel(value = "Resource", description = "资源")
public class Resource extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 资源名称
     */
    @ApiModelProperty(value = "资源名称")
    @Length(max = 20, message = "资源名称长度不能超过20")
    @TableField("name_")
    private String name;

    /**
     * 功能描述
     */
    @ApiModelProperty(value = "功能描述")
    @Length(max = 200, message = "功能描述长度不能超过200")
    @TableField("desc_")
    private String desc;

    /**
     * 资源编码
     */
    @ApiModelProperty(value = "资源编码")
    @Length(max = 255, message = "资源编码长度不能超过255")
    @TableField("code_")
    private String code;

    /**
     * 资源类型
     * #ResourceType{MENU:菜单;OPT:按钮;}
     */
    @ApiModelProperty(value = "资源类型")
    @TableField("type_")
    private ResourceType type;

    /**
     * 是否公有资源
     * 就是无需分配就可以访问的。所有人可见
     */
    @ApiModelProperty(value = "是否公有资源")
    @NotNull(message = "是否公有资源不能为空")
    @TableField("is_public")
    private Boolean isPublic;

    /**
     * 路径
     */
    @ApiModelProperty(value = "路径")
    @Length(max = 100, message = "路径长度不能超过100")
    @TableField("url_")
    private String url;

    /**
     * 打开方式
     * #UrlOpenMethod{SELF:相同框架;TOP:当前页;BLANK:新建窗口;PAREN:父窗口}
     */
    @ApiModelProperty(value = "打开方式")
    @TableField("target_")
    private UrlOpenMethod target;

    /**
     * 是否启用
     */
    @ApiModelProperty(value = "是否启用")
    @NotNull(message = "是否启用不能为空")
    @TableField("is_enable")
    private Boolean isEnable;

    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    @TableField("sortvalue")
    private Integer sortvalue;

    /**
     * 应用CODE
     * #c_auth_application
     */
    @ApiModelProperty(value = "应用CODE")
    @Length(max = 64, message = "应用CODE长度不能超过64")
    @TableField("app_code")
    private String appCode;

    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标")
    @Length(max = 255, message = "菜单图标长度不能超过255")
    @TableField("icon_")
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
    public Resource(Long id, Long createUser, LocalDateTime createTime, Long updateUser, LocalDateTime updateTime,
                    String name, String desc, String code, ResourceType type, Boolean isPublic,
                    String url, UrlOpenMethod target, Boolean isEnable, Integer sortvalue, String appCode, String icon,
                    String group, Long parentId) {
        this.id = id;
        this.createUser = createUser;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
        this.name = name;
        this.desc = desc;
        this.code = code;
        this.type = type;
        this.isPublic = isPublic;
        this.url = url;
        this.target = target;
        this.isEnable = isEnable;
        this.sortvalue = sortvalue;
        this.appCode = appCode;
        this.icon = icon;
        this.group = group;
        this.parentId = parentId;
    }

}
