package top.tangyh.lamp.authority.dto.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "标题")
    private String title;
    @Schema(description = "图标")
    private String icon = "";
    @Schema(description = "面包屑")
    private Boolean breadcrumb = true;

    @Schema(description = "是否忽略KeepAlive缓存")
    private Boolean ignoreKeepAlive;
    @Schema(description = "是否固定标签")
    private Boolean affix = false;
    @Schema(description = "内嵌iframe的地址")
    private String frameSrc;
    @Schema(description = "指定该路由切换的动画名")
    private String transitionName;
    @Schema(description = "隐藏该路由在面包屑上面的显示")
    private Boolean hideBreadcrumb = false;
    @Schema(description = "如果该路由会携带参数，且需要在tab页上面显示。则需要设置为true")
    private Boolean carryParam = false;
    @Schema(description = "当前激活的菜单。用于配置详情页时左侧激活的菜单路径")
    private String currentActiveMenu;
    @Schema(description = "当前路由不再标签页显示")
    private Boolean hideTab = false;
    @Schema(description = "当前路由不再菜单显示")
    private Boolean hideMenu = false;
}
