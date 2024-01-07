package top.tangyh.lamp.common.cache;

/**
 * 用于同一管理和生成缓存的key， 避免多个项目使用的key重复
 * <p>
 * 使用@Cacheable时， 其value值一定要在此处指定
 *
 * @author zuihou
 * @date 2020/10/21
 */
public interface CacheKeyTable {

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


    //------------------

    // 权限系统缓存 start
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
     * 参数 前缀
     * 完整key: parameter_key:{key} -> obj
     */
    String PARAMETER_KEY = "parameter_key";
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
     * 用户注册 前缀
     * 完整key: register:{注册类型}:{手机号}
     */
    String REGISTER_USER = "register";

    interface System {

        /**
         * 租户
         */
        String TENANT = "def_tenant";
        /**
         * 应用
         */
        String APPLICATION = "def_application";
        /** 应用的资源 */
        String APPLICATION_RESOURCE = "app_res";
        /**
         * 默认字典
         */
        String DICT = "def_dict";
        /**
         * 默认参数
         */
        String DEF_PARAMETER = "def_parameter";

        /**
         * 用户 前缀
         */
        String DEF_USER = "def_user";
        /**
         * 客户端
         */
        String DEF_CLIENT = "def_client";

        /**
         * 租户拥有的资源
         */
        String TENANT_APPLICATION_RESOURCE = "t_a_r";
        /**
         * 租户拥有的应用
         */
        String TENANT_APPLICATION = "t_a";

        /**
         * 资源
         */
        String RESOURCE = "dr";
        /**
         * 资源接口
         */
        String RESOURCE_API = "dra";
    }


    /**
     * 消息服务缓存 start
     */
    interface Base {

        /**
         * 租户自定义字典
         */
        String BASE_DICT = "base_dict";
        /**
         * 组织 前缀
         */
        String BASE_ORG = "base_org";
        /**
         * 岗位 前缀
         */
        String BASE_POSITION = "base_position";
        /**
         * 员工 前缀
         */
        String BASE_EMPLOYEE = "base_employee";
        /**
         * 全局员工 前缀
         */
        String DEF_USER_TENANT = "def_user_tenant";

        /**
         * 角色 前缀
         * 完整key: role:{roleId}
         */
        String ROLE = "role";
        /**
         * 角色拥有那些资源 前缀
         * 完整key: role_resource:{ROLE_ID} -> [RESOURCE_ID, ...]
         */
        String ROLE_RESOURCE = "role_resource";
        /**
         * 员工拥有那些角色 前缀
         * 完整key: employee_role:{EMPLOYEE_ID} -> [ROLE_ID, ...]
         */
        String EMPLOYEE_ROLE = "employee_role";

        /**
         * 角色拥有那些组织 前缀
         * 完整key: employee_org:{EMPLOYEE_ID} -> [ORG_ID, ...]
         */
        String EMPLOYEE_ORG = "employee_org";

        /**
         * 角色拥有那些组织 前缀
         * 完整key: org_role:{ORG_ID} -> [ROLE_ID, ...]
         */
        String ORG_ROLE = "org_role";
    }
    // 消息服务缓存 end


}
