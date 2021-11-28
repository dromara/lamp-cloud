package top.tangyh.lamp.authority.dto.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 树配置属性相关
 *
 * 代码参考 hutool
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

    private String titleKey = "title";
    private String iconKey = "icon";
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
}
