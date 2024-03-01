package top.tangyh.lamp.base.vo.result.user;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 树配置属性相关
 * <p>
 * 代码参考hutool
 *
 * @author tangyh
 */
@Getter
@Setter
@Accessors(chain = true)
public class RouterMetaConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 默认属性配置对象
     */
    public static RouterMetaConfig DEFAULT_CONFIG = new RouterMetaConfig();

    /* 公共属性 start */
    private String titleKey = "title";
    private String iconKey = "icon";
    /* 公共属性 end */

    /* vben 专用属性 start */
    private String ignoreKeepAliveKey = "ignoreKeepAlive";
    private String affixKey = "affix";
    private String frameSrcKey = "frameSrc";
    private String transitionNameKey = "transitionName";
    private String hideBreadcrumbKey = "hideBreadcrumb";
    private String carryParamKey = "carryParam";
    private String currentActiveMenuKey = "currentActiveMenu";

    private String hideTabKey = "hideTab";
    private String hideMenuKey = "hideMenu";
    private String hideChildrenInMenuKey = "hideChildrenInMenu";
    private String typeKey = "type";
    private String contentKey = "content";
    private String dotKey = "dot";
    /* vben 专用属性 end */

    /* soybean 专用属性 start */
    private String hideInMenuKey = "hideInMenu";
    private String activeMenuKey = "activeMenu";
    private String i18nKeyKey = "i18nKey";
    private String keepAliveKey = "keepAlive";
    private String constantKey = "constant";
    private String localIconKey = "localIcon";
    private String orderKey = "order";
    private String hrefKey = "href";
    private String multiTabKey = "multiTab";
    private String fixedIndexInTabKey = "fixedIndexInTab";
    /* soybean 专用属性 end */
}
