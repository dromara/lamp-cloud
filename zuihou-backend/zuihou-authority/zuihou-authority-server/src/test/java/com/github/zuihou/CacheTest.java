package com.github.zuihou;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CacheTest {


    @Test
    public void test() {
        LoadingCache<String, Map<String, Object>> caches = Caffeine.newBuilder()
                .maximumSize(10)
                .refreshAfterWrite(10000, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Map<String, Object>>() {
                           @Nullable
                           @Override
                           public Map<String, Object> load(@NonNull String s) throws Exception {

                               Map<String, Object> map = new HashMap<>();
                               map.put("aaa", "aaa");
                               return map;
                           }
                       }
                );


        Map<String, Object> aaa = caches.get("aaa");
        System.out.println(aaa);
        Map<String, Object> bbb = caches.get("aaa");
        System.out.println(bbb);
        Map<String, Object> bbb1 = caches.get("aaa1");
        System.out.println(bbb1);

    }

}
