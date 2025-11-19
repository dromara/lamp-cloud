package top.tangyh.lamp.common.cache;

import top.tangyh.basic.model.cache.CacheKeyBuilder;

/**
 * 缓存模块
 *
 * @author zuihou
 * @date 2020/10/21
 */
public abstract class CacheKeyModular implements CacheKeyBuilder {
    /**
     * 多个服务都会使用的缓存
     */
    public static final String COMMON = "common";
    /**
     * 仅基础服务base使用的缓存
     */
    public static final String BASE = "base";
    /**
     * 仅认证服务oauth使用的缓存
     */
    public static final String OAUTH = "oauth";
    /**
     * 仅租户服务tenant使用的缓存
     */
    public static final String SYSTEM = "system";


}
