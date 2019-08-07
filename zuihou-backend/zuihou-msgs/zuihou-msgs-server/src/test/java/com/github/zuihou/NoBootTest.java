package com.github.zuihou;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import org.junit.Test;

/**
 * 简单工具测试类
 *
 * @author zuihou
 * @date 2019/08/06
 */
public class NoBootTest {
    @Test
    public void test() {
        Cache<String, Object> cache = Caffeine.newBuilder()
                .maximumSize(100)
                .build();

        Object val = cache.get("zuihouaa", (key) -> "延迟加载" + key);
        System.out.println(val);

        System.out.println(cache.getIfPresent("zuihouaa"));

    }
}
