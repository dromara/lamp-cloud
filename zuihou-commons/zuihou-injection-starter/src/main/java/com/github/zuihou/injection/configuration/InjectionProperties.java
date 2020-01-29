package com.github.zuihou.injection.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置类
 *
 * @author zuihou
 * @create 2020年01月19日09:11:19
 */
@Data
@ConfigurationProperties("zuihou.injection")
public class InjectionProperties {
    /**
     * 是否启用远程查询
     */
    private Boolean enabled = true;
    /**
     * 是否启用aop注解方式
     */
    private Boolean aopEnabled = true;
    /**
     * 是否启用本地缓存
     * <p>
     * 注意：本地缓存开启后，会存在短暂的数据不一致情况， 所以对数据正确性有要求的项目建议禁用，然后在@RemoteField.method 方法上执行添加缓存！
     */
    private Boolean guavaEnabled = false;
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
