package com.github.zuihou.authority.dto.auth;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.github.zuihou.authority.enumeration.auth.MenuType;
import com.github.zuihou.authority.enumeration.auth.TargetType;

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
 * @since 2019-07-03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "MenuSaveDTO", description = "菜单")
public class MenuSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 资源名称
     */
    @ApiModelProperty(value = "菜单名称")
    @NotEmpty(message = "菜单名称不能为空")
    @Length(max = 20, message = "菜单名称长度不能超过20")
    private String name;
    /**
     * 功能描述
     */
    @ApiModelProperty(value = "功能描述")
    @Length(max = 200, message = "功能描述长度不能超过200")
    private String describe;
    /**
     * 菜单编码
     */
    @ApiModelProperty(value = "菜单编码")
    @Length(max = 255, message = "菜单编码长度不能超过255")
    private String code;
    /**
     * 菜单类型
     * #MenuType{MENU:菜单;DIR:目录;}
     */
    @ApiModelProperty(value = "菜单类型")
    private MenuType menuType;
    /**
     * 是否公开菜单
     * 就是无需分配就可以访问的。所有人可见
     */
    @ApiModelProperty(value = "是否公开菜单")
    private Boolean isPublic;
    /**
     * 菜单路径
     */
    @ApiModelProperty(value = "菜单路径")
    @Length(max = 255, message = "菜单路径长度不能超过255")
    private String href;
    /**
     * 打开方式
     * #TargetType{SELF:_self,相同框架;TOP:_top,当前页;BLANK:_blank,新建窗口;PAREN:_parent,父窗口}
     */
    @ApiModelProperty(value = "打开方式")
    private TargetType target;
    /**
     * 是否启用
     */
    @ApiModelProperty(value = "是否启用")
    private Boolean isEnable;
    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    private Integer sortvalue;
    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标")
    @Length(max = 255, message = "菜单图标长度不能超过255")
    private String icon;
    /**
     * 菜单分组
     */
    @ApiModelProperty(value = "菜单分组")
    @Length(max = 20, message = "菜单分组长度不能超过20")
    private String group;
    /**
     * 父级菜单id
     */
    @ApiModelProperty(value = "父级菜单id")
    private Long parentId;

}
