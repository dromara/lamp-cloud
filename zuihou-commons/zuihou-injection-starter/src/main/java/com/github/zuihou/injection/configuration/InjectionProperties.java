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
     * 本地缓存配置信息
     */
    private GuavaCache guavaCache = new GuavaCache();

    @Data
    public static class GuavaCache {
        /**
         * 是否启用本地缓存
         * <p>
         * 注意：本地缓存开启后，会存在短暂的数据不一致情况(由guavaCacheRefreshWriteTime决定)， 所以对数据正确性有要求的项目建议禁用，然后在@RemoteField.method 方法上执行添加redis等缓存！
         */
        private Boolean enabled = true;
        /**
         * guava缓存的 最大数
         */
        private Integer maximumSize = 1000;
        /**
         * guava更新缓存的下一次时间,分钟
         */
        private Integer refreshWriteTime = 10;
        /**
         * guava自动刷新缓存的线程数量
         */
        private Integer refreshThreadPoolSize = 10;
    }
}
