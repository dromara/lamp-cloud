package com.github.zuihou.authority.dto.auth;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

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
 * @since 2019-07-27
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
     * 菜单名称
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
     * 资源编码
     */
    @ApiModelProperty(value = "资源编码")
    @Length(max = 255, message = "资源编码长度不能超过255")
    private String code;
    /**
     * 是否公开菜单
     * 就是无需分配就可以访问的。所有人可见
     */
    @ApiModelProperty(value = "是否公开菜单")
    private Boolean isPublic;
    /**
     * 资源路径
     */
    @ApiModelProperty(value = "资源路径")
    @Length(max = 255, message = "资源路径长度不能超过255")
    private String path;
    /**
     * 是否启用
     */
    @ApiModelProperty(value = "是否启用")
    private Boolean isEnable;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sortValue;
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
