package top.tangyh.lamp.base.vo.result.user;

import cn.hutool.core.util.ObjectUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * Vue路由 Meta
 *
 * @author zuihou
 * @date 2019-10-20 15:17
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RouterMeta extends LinkedHashMap<String, Object> implements Serializable {

    private static final long serialVersionUID = 5499925008927195914L;


    @JsonIgnore
    private final RouterMetaConfig routerMetaConfig;

    public RouterMeta() {
        this(null);
    }

    /**
     * 构造
     *
     * @param routerMetaConfig 配置
     */
    public RouterMeta(RouterMetaConfig routerMetaConfig) {
        this.routerMetaConfig = ObjectUtil.defaultIfNull(routerMetaConfig, RouterMetaConfig.DEFAULT_CONFIG);
    }

    @Schema(description = "标题")
    public String getTitle() {
        return (String) this.get(routerMetaConfig.getTitleKey());
    }

    public RouterMeta setTitle(String title) {
        this.put(routerMetaConfig.getTitleKey(), title);
        return this;
    }

    @Schema(description = "图标")
    public String getIcon() {
        return (String) this.get(routerMetaConfig.getIconKey());
    }

    public RouterMeta setIcon(String icon) {
        this.put(routerMetaConfig.getIconKey(), icon);
        return this;
    }

    @Schema(description = "是否固定标签")
    public Boolean getAffix() {
        return (Boolean) this.get(routerMetaConfig.getAffixKey());
    }

    public RouterMeta setAffix(Boolean affix) {
        this.put(routerMetaConfig.getAffixKey(), affix);
        return this;
    }

    @Schema(description = "是否忽略KeepAlive缓存")
    public Boolean getIgnoreKeepAlive() {
        return (Boolean) this.get(routerMetaConfig.getIgnoreKeepAliveKey());
    }

    public RouterMeta setIgnoreKeepAlive(Boolean ignoreKeepAlive) {
        this.put(routerMetaConfig.getIgnoreKeepAliveKey(), ignoreKeepAlive);
        return this;
    }

    @Schema(description = "内嵌iframe的地址")
    public String getFrameSrc() {
        return (String) this.get(routerMetaConfig.getFrameSrcKey());
    }

    public RouterMeta setFrameSrc(String frameSrc) {
        this.put(routerMetaConfig.getFrameSrcKey(), frameSrc);
        return this;
    }

    @Schema(description = "指定该路由切换的动画名")
    public String getTransitionName() {
        return (String) this.get(routerMetaConfig.getTransitionNameKey());
    }

    public RouterMeta setTransitionName(String transitionName) {
        this.put(routerMetaConfig.getTransitionNameKey(), transitionName);
        return this;
    }

    @Schema(description = "隐藏该路由在面包屑上面的显示")
    public Boolean getHideBreadcrumb() {
        return (Boolean) this.get(routerMetaConfig.getHideBreadcrumbKey());
    }

    public RouterMeta setHideBreadcrumb(Boolean hideBreadcrumb) {
        this.put(routerMetaConfig.getHideBreadcrumbKey(), hideBreadcrumb);
        return this;
    }

    @Schema(description = "如果该路由会携带参数，且需要在tab页上面显示。则需要设置为true")
    public Boolean getCarryParam() {
        return (Boolean) this.get(routerMetaConfig.getCarryParamKey());
    }

    public RouterMeta setCarryParam(Boolean carryParam) {
        this.put(routerMetaConfig.getCarryParamKey(), carryParam);
        return this;
    }

    @Schema(description = "当前激活的菜单。用于配置详情页时左侧激活的菜单路径")
    public String getCurrentActiveMenu() {
        return (String) this.get(routerMetaConfig.getCurrentActiveMenuKey());
    }

    public RouterMeta setCurrentActiveMenu(String currentActiveMenu) {
        this.put(routerMetaConfig.getCurrentActiveMenuKey(), currentActiveMenu);
        return this;
    }

    @Schema(description = "当前路由不再标签页显示")
    public Boolean getHideTab() {
        return (Boolean) this.get(routerMetaConfig.getHideTabKey());
    }

    public RouterMeta setHideTab(Boolean hideTab) {
        this.put(routerMetaConfig.getHideTabKey(), hideTab);
        return this;
    }

    @Schema(description = "当前路由不再菜单显示")
    public Boolean getHideMenu() {
        return (Boolean) this.get(routerMetaConfig.getHideMenuKey());
    }

    public RouterMeta setHideMenu(Boolean hideMenu) {
        this.put(routerMetaConfig.getHideMenuKey(), hideMenu);
        return this;
    }

    @Schema(description = "用于隐藏子菜单")
    public Boolean getHideChildrenInMenu() {
        return (Boolean) this.get(routerMetaConfig.getHideChildrenInMenuKey());
    }

    public RouterMeta setHideChildrenInMenu(Boolean hideChildrenInMenu) {
        this.put(routerMetaConfig.getHideChildrenInMenuKey(), hideChildrenInMenu);
        return this;
    }

    @Schema(description = "菜单小圆点类型")
    public String getType() {
        return (String) this.get(routerMetaConfig.getTypeKey());
    }

    public RouterMeta setType(String type) {
        this.put(routerMetaConfig.getTypeKey(), type);
        return this;
    }

    @Schema(description = "是否显示内容")
    public String getContent() {
        return (String) this.get(routerMetaConfig.getContentKey());
    }

    public RouterMeta setContent(String content) {
        this.put(routerMetaConfig.getContentKey(), content);
        return this;
    }

    @Schema(description = "是否显示小圆点")
    public Boolean getDot() {
        return (Boolean) this.get(routerMetaConfig.getDotKey());
    }

    @Schema(description = "是否显示小圆点")
    public RouterMeta setDot(Boolean dot) {
        this.put(routerMetaConfig.getDotKey(), dot);
        return this;
    }
}
