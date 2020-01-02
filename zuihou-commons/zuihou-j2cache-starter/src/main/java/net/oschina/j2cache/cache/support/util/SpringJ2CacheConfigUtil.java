package net.oschina.j2cache.cache.support.util;

import net.oschina.j2cache.J2CacheConfig;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.StandardEnvironment;

public class SpringJ2CacheConfigUtil {

    /**
     * 从spring环境变量中查找j2cache配置
     */
    public final static J2CacheConfig initFromConfig(StandardEnvironment environment) {
        J2CacheConfig config = new J2CacheConfig();
        config.setSerialization(environment.getProperty("j2cache.serialization"));
        config.setBroadcast(environment.getProperty("j2cache.broadcast"));
        config.setL1CacheName(environment.getProperty("j2cache.L1.provider_class"));
        config.setL2CacheName(environment.getProperty("j2cache.L2.provider_class"));
        config.setSyncTtlToRedis(!"false".equalsIgnoreCase(environment.getProperty("j2cache.sync_ttl_to_redis")));
        config.setDefaultCacheNullObject("true".equalsIgnoreCase(environment.getProperty("j2cache.default_cache_null_object")));
        String l2_config_section = environment.getProperty("j2cache.L2.config_section");
        if (l2_config_section == null || l2_config_section.trim().equals("")) {
            l2_config_section = config.getL2CacheName();
        }
        String l2_section = l2_config_section;
        //配置在 application.yml 或者 j2cache.properties 中时，这里能正常读取
        environment.getPropertySources().forEach(a -> {
            if (a instanceof MapPropertySource) {
                MapPropertySource c = (MapPropertySource) a;
                c.getSource().forEach((k, v) -> {
                    String key = k;
                    if (key.startsWith(config.getBroadcast() + ".")) {
                        config.getBroadcastProperties().setProperty(key.substring((config.getBroadcast() + ".").length()),
                                environment.getProperty(key));
                    }
                    if (key.startsWith(config.getL1CacheName() + ".")) {
                        config.getL1CacheProperties().setProperty(key.substring((config.getL1CacheName() + ".").length()),
                                environment.getProperty(key));
                    }
                    if (key.startsWith(l2_section + ".")) {
                        config.getL2CacheProperties().setProperty(key.substring((l2_section + ".").length()),
                                environment.getProperty(key));
                    }
                });
            }
        });

        //配置在 nacos 中时，上面那段代码无法获取配置
        if (config.getL1CacheProperties().isEmpty() || config.getL2CacheProperties().isEmpty() || config.getBroadcastProperties().isEmpty()) {
            environment.getPropertySources().forEach(ps -> {
                String[] propertyNames = new String[]{};
                if (ps instanceof CompositePropertySource) {
                    CompositePropertySource cps = (CompositePropertySource) ps;
                    propertyNames = cps.getPropertyNames();
                } else if (ps instanceof EnumerablePropertySource) {
                    EnumerablePropertySource eps = (EnumerablePropertySource) ps;
                    propertyNames = eps.getPropertyNames();
                }
                setProperty(config, environment, l2_section, propertyNames);
            });
        }
        return config;
    }

    private static void setProperty(J2CacheConfig config, StandardEnvironment environment, String l2_section, String[] propertyNames) {
        for (String key : propertyNames) {
            if (key.startsWith(config.getBroadcast() + ".")) {
                config.getBroadcastProperties().setProperty(key.substring((config.getBroadcast() + ".").length()),
                        environment.getProperty(key));
            }
            if (key.startsWith(config.getL1CacheName() + ".")) {
                config.getL1CacheProperties().setProperty(key.substring((config.getL1CacheName() + ".").length()),
                        environment.getProperty(key));
            }
            if (key.startsWith(l2_section + ".")) {
                config.getL2CacheProperties().setProperty(key.substring((l2_section + ".").length()),
                        environment.getProperty(key));
            }
        }

    }
}
