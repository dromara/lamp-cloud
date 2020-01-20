package com.github.zuihou.remotedata.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置类
 *
 * @author zuihou
 * @create 2020年01月19日09:11:19
 */
@Data
@ConfigurationProperties("zuihou.remote")
public class RemoteProperties {
    /**
     * 是否启用远程查询
     */
    private Boolean enabled = true;
    /**
     * 是否启用aop注解方式
     */
    private Boolean aopEnabled = true;
    /**
     * guava缓存的键值数
     */
    private Integer guavaCacheNumMaxSize = 1000;
    /**
     * guava更新混存的下一次时间,分钟
     */
    private Integer guavaCacheRefreshWriteTime = 10;
    /**
     * guava
     */
    private Integer guavaCacheRefreshThreadPoolSize = 10;

    public Integer getGuavaCacheNumMaxSize() {
        return guavaCacheNumMaxSize;
    }

    public void setGuavaCacheNumMaxSize(Integer guavaCacheNumMaxSize) {
        this.guavaCacheNumMaxSize = guavaCacheNumMaxSize;
    }

    public Integer getGuavaCacheRefreshWriteTime() {
        return guavaCacheRefreshWriteTime;
    }

    public void setGuavaCacheRefreshWriteTime(Integer guavaCacheRefreshWriteTime) {
        this.guavaCacheRefreshWriteTime = guavaCacheRefreshWriteTime;
    }

}
