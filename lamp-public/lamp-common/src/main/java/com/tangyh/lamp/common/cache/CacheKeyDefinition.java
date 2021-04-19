package com.tangyh.lamp.common.cache;

/**
 * 用于同一管理和生成缓存的key， 避免多个项目使用的key重复
 * <p>
 * 使用@Cacheable时， 其value值一定要在此处指定
 *
 * @author zuihou
 * @date 2020/10/21
 */
public interface CacheKeyDefinition {
    // 权限系统缓存 start

    /**
     * 验证码 前缀
     * 完整key: captcha:{key} -> str
     */
    String CAPTCHA = "captcha";
    /**
     * token 前缀
     * 完整key： token:{token} -> userid
     */
    String TOKEN = "token";


    /**
     * 菜单 前缀
     * 完整key: menu:{menuId} -> obj
     */
    String MENU = "menu";
    /**
     * 组织 前缀
     * 完整key: station:{stationId} -> obj
     */
    String ORG = "org";
    /**
     * 岗位 前缀
     * 完整key: station:{stationId} -> obj
     */
    String STATION = "station";

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
     * 完整key: role_menu:{ROLE_ID} -> [MENU_ID, MENU_ID, ...]
     */
    String ROLE_MENU = "role_menu";
    /**
     * 角色拥有那些资源 前缀
     * 完整key: role_resource:{ROLE_ID} -> [RESOURCE_ID, ...]
     */
    String ROLE_RESOURCE = "role_resource";
//    /**
//     * 角色拥有那些组织 前缀
//     * 完整key: role_org:{ROLE_ID} -> [ORG_ID, ...]
//     */
//    String ROLE_ORG = "role_org";

    /**
     * 用户 前缀
     * 完整key: user:{USER_ID} -> obj
     */
    String USER = "user";
    /**
     * 用户 前缀
     * 完整key: user_account:{account} -> id
     */
    String USER_ACCOUNT = "user_account";

    /**
     * 用户拥有那些角色 前缀
     * 完整key: user_role:{USER_ID} -> [ROLE_ID, ...]
     */
    String USER_ROLE = "user_role";
    /**
     * 用户拥有的菜单 前缀
     * 完整key: user_menu:{userId} -> [MENU_ID, MENU_ID, ...]
     */
    String USER_MENU = "user_menu";
    /**
     * 用户拥有的资源 前缀
     * 完整key: user_resource:{userId} -> [RESOURCE_ID, ...]
     */
    String USER_RESOURCE = "user_resource";


    /**
     * 系统URI 前缀
     * <p>
     * 完整key: system_api:{id} -> [ID, ...]
     */
    String SYSTEM_API = "system_api";

    /**
     * 总 登录次数
     * total_login_pv:{TENANT} -> Long
     */
    String TOTAL_LOGIN_PV = "total_login_pv";
    /**
     * 今日 登录次数
     * today_login_pv:{TENANT}:{today} -> Long
     */
    String TODAY_LOGIN_PV = "today_login_pv";
    /**
     * 今日登录总ip
     * today_login_iv:{TENANT}:{today} -> int
     */
    String TODAY_LOGIN_IV = "today_login_iv";
    /**
     * 今日登录总ip
     * TOTAL_LOGIN_IV:{TENANT} -> int
     */
    String TOTAL_LOGIN_IV = "total_login_iv";

    /**
     * 今日 PV
     * today_pv:{TENANT} -> int
     */
    String TODAY_PV = "today_pv";
    /**
     * 总 PV
     * total_pv:{TENANT} -> int
     */
    String TOTAL_PV = "total_pv";


    /**
     * 最近10访问记录
     * login_log_tenday:{TENANT}:{today}:{account} -> Map
     */
    String LOGIN_LOG_TEN_DAY = "login_log_tenday";
    /**
     * 登录总次数
     * login_log_browser:{TENANT} -> Map
     */
    String LOGIN_LOG_BROWSER = "login_log_browser";
    /**
     * 登录总次数
     * login_log_system{TENANT} -> Map
     */
    String LOGIN_LOG_SYSTEM = "login_log_system";

    /**
     * 地区 前缀
     * 完整key: area:{id} -> obj
     */
    String AREA = "area";
    /**
     * 所有地区 前缀
     * 完整key: area_all -> [AREA_ID]
     */
    String AREA_ALL = "area_all";

    /**
     * 字典项 前缀
     * 完整key: dictionary:{id} -> obj
     */
    String DICTIONARY = "dictionary";
    /**
     * 字典类型 前缀
     * 完整key:  {tenant}:dictionary_type:{type}
     * field:   {code}
     * value:   itemId
     */
    String DICTIONARY_TYPE = "dictionary_type";

    /**
     * 参数 前缀
     * 完整key: parameter_key:{key} -> obj
     */
    String PARAMETER_KEY = "parameter_key";
    /**
     * 应用 前缀
     * 完整key: application:{id} -> obj
     */
    String APPLICATION = "application";
    /**
     * 应用 前缀
     * 完整key: application:{clientId}:{clientSecret} -> id
     */
    String APPLICATION_CLIENT = "application_client";
//    /**
//     * 用户登录的客户端 前缀： 用于记录用户在那几个设备上登录了
//     * 完整key: user_login_client:{userid} -> [client, client, ...] (Set)
//     */
//    String USER_LOGIN_CLIENT = "user_login_client";

    /**
     * 在用用户 前缀
     * 完整key: online:{userid} -> token (String)
     */
    String ONLINE = "online";

    /**
     * 用户token 前缀
     * 完整key: token_user_id:{token} -> userid (Long)
     */
    String TOKEN_USER_ID = "token_user_id";


    /**
     * 租户 前缀
     * 完整key: tenant:{id} -> obj
     */
    String TENANT = "tenant";
    /**
     * 租户 前缀
     * 完整key: tenant_code:{code} -> id
     */
    String TENANT_CODE = "tenant_code";

    // 权限系统缓存 end


    // 消息服务缓存 start
    /**
     * 用户注册 前缀
     * 完整key: register:{注册类型}:{手机号}
     */
    String REGISTER_USER = "register";
    // 消息服务缓存 end


    /**
     * 阻止列表
     */
    String BLOCKLIST_ID = "gateway:blocklist:id";
    String BLOCKLIST = "gateway:blocklist";
    /**
     * 限流
     */
    String RATE_LIMITER_ID = "gateway:ratelimiter:id";
    String RATE_LIMITER = "gateway:ratelimiter";

}
