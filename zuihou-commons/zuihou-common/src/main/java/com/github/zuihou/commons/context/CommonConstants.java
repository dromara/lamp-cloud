package com.github.zuihou.commons.context;

/**
 * 全局常量表
 * @author zuihou
 * @createTime 2017-12-13 15:44
 */
public class CommonConstants {
    /** 默认的根节点path */
    public static final String ROOT_PATH_DEF = ",";
    /** 默认的父id */
    public static final Long PARENT_ID_DEF = -1L;
    /** 帐号密码加密 salt */
    public static final int PW_ENCORDER_SALT = 12;
    /** 默认的菜单组code */
    public static final String MENU_GROUP_CODE_DEF = "DEF";

    /** 通过zuul过滤后，统一添加的header */
    public static final String ZUUL_HEADER_KEY_APP_ID = "appId";
    /** 临时存在ThreadLocal中的 appId key */
    public static final String CONTEXT_KEY_APP_ID = "currentAppId";
    /** 临时存在ThreadLocal中的 adminId key */
    public static final String CONTEXT_KEY_ADMIN_ID = "currentAdminId";
    /** 临时存在ThreadLocal中的 登录名 key */
    public static final String CONTEXT_KEY_USERNAME = "currentUserName";
    /** 临时存在ThreadLocal中的 昵称 key */
    public static final String CONTEXT_KEY_NAME = "currentName";
    /** 临时存在ThreadLocal中的 token key */
    public static final String CONTEXT_KEY_APP_TOKEN = "currentAppToken";

    /**  */
    public static final String JWT_KEY_ADMIN_ID = "adminId";
    /**  */
    public static final String JWT_KEY_NAME = "name";
    /**  */
    public static final String JWT_KEY_APP_ID = "appId";
    /**  */
    public static final String JWT_KEY_USER_NAME = "userName";
    /**  */
    public static final String JWT_KEY_HOST_ID = "hostId";
}
