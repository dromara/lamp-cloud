package com.github.zuihou.j2cache;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;

/**
 * 覆盖 SpringCache 相关配置
 *
 * @author zuihou
 * @date 2019-10-18 09:06
 */
public class MyCacheConfig extends CachingConfigurerSupport {

    /**
     * 解决注解：Cacheable 没有指定key时，会将key生成为 ${value}:SimpleKey []
     * eg： @Cacheable(value = "zuihou") ->  zuihou:SimpleKey []
     *
     * @return
     */
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, objects) -> {
//            StringBuilder sb = new StringBuilder();
//            sb.append(target.getClass().getName());
//            sb.append(StrPool.COLON);
//            sb.append(method.getName());
//            for (Object obj : objects) {
//                if (obj != null) {
//                    sb.append(StrPool.COLON);
//                    sb.append(obj.toString());
//                }
//            }
//            return sb.toString();
            return "";
        };
    }

}
