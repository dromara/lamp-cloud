package com.tangyh.lamp.authority.dto.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Vue路由 Meta
 *
 * @author zuihou
 * @date 2019-10-20 15:17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RouterMeta implements Serializable {

    private static final long serialVersionUID = 5499925008927195914L;

    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "图标")
    private String icon = "";
    @ApiModelProperty(value = "面包屑")
    private Boolean breadcrumb = true;

    @ApiModelProperty(value = "是否忽略KeepAlive缓存")
    private Boolean ignoreKeepAlive;
    @ApiModelProperty(value = "是否固定标签")
    private Boolean affix = false;
    @ApiModelProperty(value = "内嵌iframe的地址")
    private String frameSrc;
    @ApiModelProperty(value = "指定该路由切换的动画名")
    private String transitionName;
    @ApiModelProperty(value = "隐藏该路由在面包屑上面的显示")
    private Boolean hideBreadcrumb = false;
    @ApiModelProperty(value = "如果该路由会携带参数，且需要在tab页上面显示。则需要设置为true")
    private Boolean carryParam = false;
    @ApiModelProperty(value = "当前激活的菜单。用于配置详情页时左侧激活的菜单路径")
    private String currentActiveMenu;
    @ApiModelProperty(value = "当前路由不再标签页显示")
    private Boolean hideTab = false;
    @ApiModelProperty(value = "当前路由不再菜单显示")
    private Boolean hideMenu = false;
}
