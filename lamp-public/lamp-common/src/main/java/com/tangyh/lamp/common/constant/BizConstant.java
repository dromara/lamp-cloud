package com.tangyh.lamp.common.constant;

/**
 * 业务常量
 *
 * @author zuihou
 * @date 2019/08/06
 */
public interface BizConstant {
    /**
     * 工具类 需要扫描的包
     */
    String UTIL_PACKAGE = "com.tangyh.basic";
    /**
     * 业务项目 需要扫描的包
     */
    String BUSINESS_PACKAGE = "com.tangyh.lamp";
    /**
     * 超级租户编码
     */
    String SUPER_TENANT = "admin";
    /**
     * 初始化的租户管理员角色
     */
    String INIT_ROLE_CODE = "SUPER_ADMIN";

    /**
     * 演示用的组织ID
     */
    Long DEMO_ORG_ID = 101L;
    /**
     * 角色前缀
     */
    String ROLE_PREFIX = "ROLE_";
    /**
     * 演示用的岗位ID
     */
    Long DEMO_STATION_ID = 101L;

    /**
     * 默认密码：123456
     */
    String DEF_PASSWORD = "123456";

    /**
     * 基础库
     */
    String BASE_DATABASE = "lamp_base";
    /**
     * 扩展库
     */
    String EXTEND_DATABASE = "lamp_extend";

    /**
     * 被T
     */
    String LOGIN_STATUS = "T";

    String AUTHORITY = "lamp-authority-server";
    String FILE = "lamp-file-server";
    String MSG = "lamp-msg-server";
    String OAUTH = "lamp-oauth-server";
    String GATE = "lamp-gateway-server";
    String BASE_EXECUTOR = "lamp-base-executor";
    String EXTEND_EXECUTOR = "lamp-extend-executor";
    String ORDER = "lamp-example-server";
    String DEMO = "lamp-demo-server";

    /**
     * 初始化数据源时json的参数，
     * method 的可选值为 {INIT_DS_PARAM_METHOD_INIT} 和 {INIT_DS_PARAM_METHOD_REMOVE}
     */
    String INIT_DS_PARAM_METHOD = "method";
    /**
     * 初始化数据源时json的参数，
     * tenant 的值为 需要初始化的租户编码
     */
    String INIT_DS_PARAM_TENANT = "tenant";
    /**
     * 初始化数据源时，需要执行的方法
     * init 表示初始化数据源
     * remove 表示删除数据源
     */
    String INIT_DS_PARAM_METHOD_INIT = "init";
    /**
     * 初始化数据源时，需要执行的方法
     * init 表示初始化数据源
     * remove 表示删除数据源
     */
    String INIT_DS_PARAM_METHOD_REMOVE = "remove";
}
