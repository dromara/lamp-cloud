package com.github.zuihou.context;

/**
 * 常量工具类
 *
 * @author zuihou
 * @date 2018/12/21
 */
public class BaseContextConstants {
    /**
     * token
     */
    public static final String TOKEN_NAME = "token";
    /**
     * 用户id
     */
    public static final String JWT_KEY_USER_ID = "userid";
    /**
     * 用户名称
     */
    public static final String JWT_KEY_NAME = "name";
    /**
     * 用户账号
     */
    public static final String JWT_KEY_ACCOUNT = "account";

    /**
     * 组织id
     */
    @Deprecated
    public static final String JWT_KEY_ORG_ID = "orgid";
    /**
     * 岗位id
     */
    @Deprecated
    public static final String JWT_KEY_STATION_ID = "stationid";

    /**
     * 租户 编码
     */
    public static final String TENANT = "tenant";
    /**
     * 动态数据库名前缀。  每个项目配置死的
     */
    public static final String DATABASE_NAME = "database_name";

//    /**
//     * 是否boot项目
//     */
//    @Deprecated
//    public static final String IS_BOOT = "boot";

    /**
     * 灰度发布版本号
     */
    public static final String GRAY_VERSION = "grayversion";
}
