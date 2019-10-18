package net.oschina.j2cache.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 相关的配置信息
 *
 * @author 小雨哥哥
 */
@ConfigurationProperties(prefix = "j2cache")
public class J2CacheConfig {
    private String configLocation = "/j2cache.properties";

    /**
     * 是否开启spring cache缓存,注意:开启后需要添加spring.cache.type=GENERIC,将缓存类型设置为GENERIC
     */
    private Boolean openSpringCache = false;

    /**
     * 缓存清除模式，
     * <ul>
     * <li>active:主动清除，二级缓存过期主动通知各节点清除，优点在于所有节点可以同时收到缓存清除</li>
     * <li>passive:被动清除，一级缓存过期进行通知各节点清除一二级缓存，</li>
     * <li> blend:两种模式一起运作，对于各个节点缓存准确以及及时性要求高的可以使用，正常用前两种模式中一个就可</li>
     * </ul>
     */
    private String cacheCleanMode = "passive";

    /**
     * 是否允许缓存空值,默认:false
     */
    private boolean allowNullValues = false;

    /**
     * 使用哪种redis客户端,默认：jedis
     * <ul>
     * <li><a href ='https://github.com/xetorthio/jedis'>jedis: https://github.com/xetorthio/jedis</a></li>
     * <li><a href ='https://github.com/lettuce-io/lettuce-core'>lettuce: https://github.com/lettuce-io/lettuce-core</a></li>
     * </ul>
     */
    private String redisClient = "jedis";

    /**
     * 是否开启二级缓存
     */
    private boolean l2CacheOpen = true;


    public String getConfigLocation() {
        return configLocation;
    }

    public void setConfigLocation(String configLocation) {
        this.configLocation = configLocation;
    }

    public Boolean getOpenSpringCache() {
        return openSpringCache;
    }

    public void setOpenSpringCache(Boolean openSpringCache) {
        this.openSpringCache = openSpringCache;
    }

    public String getCacheCleanMode() {
        return cacheCleanMode;
    }

    public void setCacheCleanMode(String cacheCleanMode) {
        this.cacheCleanMode = cacheCleanMode;
    }

    public boolean isAllowNullValues() {
        return allowNullValues;
    }

    public void setAllowNullValues(boolean allowNullValues) {
        this.allowNullValues = allowNullValues;
    }

    public String getRedisClient() {
        return redisClient;
    }

    public void setRedisClient(String redisClient) {
        this.redisClient = redisClient;
    }

    public boolean getL2CacheOpen() {
        return l2CacheOpen;
    }

    public void setL2CacheOpen(boolean l2CacheOpen) {
        this.l2CacheOpen = l2CacheOpen;
    }

}
