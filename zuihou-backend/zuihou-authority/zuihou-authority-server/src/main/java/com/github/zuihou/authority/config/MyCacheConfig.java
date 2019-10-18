//package com.github.zuihou.authority.config;
//
//import lombok.SneakyThrows;
//import net.oschina.j2cache.J2CacheBuilder;
//import net.oschina.j2cache.J2CacheConfig;
//import net.oschina.j2cache.springcache.J2CacheSpringCacheManageAdapter;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.interceptor.KeyGenerator;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@EnableCaching
//public class MyCacheConfig extends CachingConfigurerSupport {
////    @Override
////    @SneakyThrows
////    public CacheManager cacheManager() {
////        // 引入配置
////        J2CacheConfig config = J2CacheConfig.initFromConfig("/j2cache.properties");
////        // 生成 J2CacheBuilder
////        J2CacheBuilder j2CacheBuilder = J2CacheBuilder.init(config);
////        // 构建适配器
////        J2CacheSpringCacheManageAdapter j2CacheSpringCacheManageAdapter = new J2CacheSpringCacheManageAdapter(j2CacheBuilder, true);
////
////        return j2CacheSpringCacheManageAdapter;
////    }
//
//
//    /**
//     * 解决注解：Cacheable 没有指定key时，会将key生成为 ${value}:SimpleKey []
//     * eg： @Cacheable(value = "zuihou") ->  zuihou:SimpleKey []
//     *
//     * @return
//     */
//    @Override
//    public KeyGenerator keyGenerator() {
//        return (target, method, objects) -> {
////            StringBuilder sb = new StringBuilder();
////            sb.append(target.getClass().getName());
////            sb.append(StrPool.COLON);
////            sb.append(method.getName());
////            for (Object obj : objects) {
////                if (obj != null) {
////                    sb.append(StrPool.COLON);
////                    sb.append(obj.toString());
////                }
////            }
////            return sb.toString();
//            return "";
//        };
//    }
//
//}
