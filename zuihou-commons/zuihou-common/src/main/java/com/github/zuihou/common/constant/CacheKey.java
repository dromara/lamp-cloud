package com.github.zuihou.common.constant;

import com.github.zuihou.utils.StrPool;
import com.google.common.base.Joiner;

/**
 * 用于同一管理和生成缓存的key， 避免多个项目使用的key重复
 *
 * 使用@Cacheable时， 其value值一定要在此处指定
 *
 * @author zuihou
 * @date 2019/08/06
 */
public interface CacheKey {
    // 权限系统缓存
    /**
     * 菜单 前缀
     * 完整key: menu:{menuId}
     */
    String MENU = "menu";
    /**
     * 用户拥有的菜单 前缀
     * 完整key: menu:user:{menuId}
     */
    String MENU_USER = "menu:user";
    /**
     * 资源 前缀
     * 完整key: resource:{resourceId}
     */
    String RESOURCE = "resource";
    /**
     * 用户拥有的资源 前缀
     * 完整key: resource:user:{resourceId}
     */
    String RESOURCE_USER = "resource";

    // 消息服务缓存
    /**
     * 用户注册 前缀
     * 完整key: register:{注册类型}:{手机号}
     */
    String REGISTER_USER = "register";


    /**
     * 构建key
     *
     * @param args
     * @return
     */
    static String build(String... args) {
        return Joiner.on(StrPool.COLON).join(args);
    }
}
