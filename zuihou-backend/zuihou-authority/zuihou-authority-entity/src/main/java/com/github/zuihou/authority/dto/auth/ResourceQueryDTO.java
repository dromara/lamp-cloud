package com.github.zuihou.authority.dto.auth;


import com.github.zuihou.authority.enumeration.auth.ResourceType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is a Description
 *
 * @author tangyh
 * @date 2019/06/05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "ResourceQueryDTO", description = "资源查询")
public class ResourceQueryDTO {
    /**
     * 资源类型 #ResourceType{Menu: 菜单; Opt: 菜单内部按钮}
     */
    @ApiModelProperty(value = "资源类型")
    private ResourceType type;

    /**
     * 菜单分组
     */
    @ApiModelProperty(value = "菜单分组")
    private String group;

    /**
     * 父资源id， 用于查询按钮
     */
    @ApiModelProperty(value = "父级资源id", notes = "父级资源id，用于按钮查询")
    private Long resourceId;
    /**
     * 登录人用户id
     */
    @ApiModelProperty(value = "当前登录人id", notes = "当前登录人id，前端不用传，自动获取")
    private Long userId;

    /**
     * 资源id
     */
    @ApiModelProperty(value = "资源id", notes = "资源id")
    private Long roleId;

}
