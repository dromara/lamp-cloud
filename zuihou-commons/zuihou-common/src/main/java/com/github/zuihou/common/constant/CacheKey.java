package com.github.zuihou.common.constant;

import com.github.zuihou.utils.StrPool;
import com.google.common.base.Joiner;

/**
 * 用于同一管理和生成缓存的key， 避免多个项目使用的key重复
 *
 * 使用@Cacheable时， 其value值一定要在此处指定
 *
 * @author zuihou
 * @date 2019/08/06
 */
public interface CacheKey {
    String REGISTER_USER = "register";

    /**
     * 构建key
     *
     * @param args
     * @return
     */
    static String build(String... args) {
        return Joiner.on(StrPool.COLON).join(args);
    }
}
