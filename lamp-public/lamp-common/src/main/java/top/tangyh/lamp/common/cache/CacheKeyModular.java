package top.tangyh.lamp.common.cache;

/**
 * 缓存模块
 *
 * @author zuihou
 * @date 2020/10/21
 */
public class CacheKeyModular {
    /**
     * 多个服务都会使用的缓存
     */
    public static final String COMMON = "common";
    /**
     * 仅基础服务base使用的缓存
     */
    public static final String BASE = "base";
    /**
     * 仅消息服务msg使用的缓存
     */
    public static final String MSG = "msg";
    /**
     * 仅认证服务oauth使用的缓存
     */
    public static final String OAUTH = "oauth";
    /**
     * 仅文件服务file使用的缓存
     */
    public static final String FILE = "file";
    /**
     * 仅租户服务tenant使用的缓存
     */
    public static final String SYSTEM = "system";
    /**
     * 仅网关服务gateway使用的缓存
     */
    public static final String GATEWAY = "gateway";

    /** 缓存key前缀， 可以在启动时覆盖该参数。
     * 系统启动时，注入。
     */
    public static String PREFIX;
}
