package com.github.zuihou.common.constant;

import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.utils.StrPool;

import cn.hutool.core.util.StrUtil;

/**
 * 用于同一管理和生成缓存的key， 避免多个项目使用的key重复
 * <p>
 * 使用@Cacheable时， 其value值一定要在此处指定
 *
 * @author zuihou
 * @date 2019/08/06
 */
public interface CacheKey {
    // 权限系统缓存 start

    /**
     * 验证码 前缀
     * 完整key: captcha:{key} -> str
     */
    String CAPTCHA = "captcha";


    /**
     * 菜单 前缀
     * 完整key: menu:{menuId} -> obj
     */
    String MENU = "menu";

    /**
     * 资源 前缀
     * 完整key: resource:{resourceId} -> obj
     */
    String RESOURCE = "resource";

    /**
     * 角色 前缀
     * 完整key: role:{roleId}
     */
    String ROLE = "role";
    /**
     * 角色拥有那些菜单 前缀
     * 完整key: role:menu:{ROLE_ID} -> [MENU_ID, MENU_ID, ...]
     */
    String ROLE_MENU = "role:menu";
    /**
     * 角色拥有那些资源 前缀
     * 完整key: role:resource:{ROLE_ID} -> [RESOURCE_ID, ...]
     */
    String ROLE_RESOURCE = "role:resource";
    /**
     * 角色拥有那些组织 前缀
     * 完整key: role:org:{ROLE_ID} -> [ORG_ID, ...]
     */
    String ROLE_ORG = "role:org";

    /**
     * 用户拥有那些角色 前缀
     * 完整key: user:role:{USER_ID} -> [ROLE_ID, ...]
     */
    String USER_ROLE = "user:role";
    /**
     * 用户拥有的菜单 前缀
     * 完整key: user:menu:{userId} -> [MENU_ID, MENU_ID, ...]
     */
    String USER_MENU = "user:menu";
    /**
     * 用户拥有的资源 前缀
     * 完整key: user:resource:{userId} -> [RESOURCE_ID, ...]
     */
    String USER_RESOURCE = "user:resource";

    /**
     * 登录总次数
     * login:log:total:{TENANT} -> Long
     */
    String LOGIN_LOG_TOTAL = "login:log:total";
    /**
     * 今日登录总次数
     * login:log:today:{TENANT}:{today} -> Long
     */
    String LOGIN_LOG_TODAY = "login:log:today";
    /**
     * 今日登录总ip
     * login:log:todayip:{TENANT}:{today} -> Map
     */
    String LOGIN_LOG_TODAY_IP = "login:log:todayip";
    /**
     * 最近10访问记录
     * login:log:tenday:{TENANT}:{today}:{account} -> Map
     */
    String LOGIN_LOG_TEN_DAY = "login:log:tenday";
    /**
     * 登录总次数
     * login:log:browser:{TENANT} -> Map
     */
    String LOGIN_LOG_BROWSER = "login:log:browser";
    /**
     * 登录总次数
     * login:log:system{TENANT} -> Map
     */
    String LOGIN_LOG_SYSTEM = "login:log:system";


    // 权限系统缓存 end


    // 消息服务缓存 start
    /**
     * 用户注册 前缀
     * 完整key: register:{注册类型}:{手机号}
     */
    String REGISTER_USER = "register";
    // 消息服务缓存 end

    /**
     * 构建key
     *
     * @param args
     * @return
     */
    static String build(Object... args) {
        if (args.length > 0) {
            return StrUtil.join(StrPool.COLON, BaseContextHandler.getTenant(), args);
        } else {
            return StrUtil.join(StrPool.COLON, BaseContextHandler.getTenant());
        }
    }
}
