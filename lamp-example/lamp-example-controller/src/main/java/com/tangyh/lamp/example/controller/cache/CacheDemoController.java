package com.tangyh.lamp.example.controller.cache;

import com.tangyh.basic.base.R;
import com.tangyh.basic.cache.model.CacheKey;
import com.tangyh.basic.cache.model.CacheKeyBuilder;
import com.tangyh.basic.cache.repository.CacheOps;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.stream.Stream;

/**
 * @author zuihou
 * @date 2020/6/7 下午9:20
 */
@Slf4j
@RestController
@RequestMapping("/cache")
@Api(value = "Cache", tags = "缓存演示")
@Setter
@RequiredArgsConstructor
public class CacheDemoController {
    private static final String DEF_KEY = "test_cache";
    private final CacheOps cacheOps;


    @GetMapping("/get")
    public R get(@RequestParam("key") String key) {
        CacheKeyBuilder builder = () -> DEF_KEY;
        CacheKey cacheKey = builder.key(key);
        return R.success(cacheOps.get(cacheKey));
    }

    @GetMapping("/getOrDef")
    public R getOrDef(@RequestParam("key") String key, @RequestParam(value = "def", required = false) String def) {
        CacheKeyBuilder builder = () -> DEF_KEY;
        CacheKey cacheKey = builder.key(key);
        return R.success(cacheOps.get(cacheKey, (k) -> def));
    }

    @GetMapping("/del")
    public R del(@RequestParam("keys") String[] keys) {
        CacheKeyBuilder builder = () -> DEF_KEY;
        CacheKey[] cacheKeys = Stream.of(keys).map(builder::key).toArray(CacheKey[]::new);
        return R.success(cacheOps.del(cacheKeys));
    }

    @GetMapping("/flushDb")
    public R flushDb() {
        cacheOps.flushDb();
        return R.success();
    }

    @GetMapping("/set")
    public R set(@RequestParam("key") String key, @RequestParam String value) {
        CacheKeyBuilder builder = () -> DEF_KEY;
        CacheKey cacheKey = builder.key(key);
//        cacheOps.set(key, new Order().setName(value).setCreateTime(LocalDateTime.now()).setId(123L));
        cacheOps.set(cacheKey, value);
        return R.success();
    }

    @GetMapping("/setExpire")
    public R setExpire(@RequestParam("key") String key, @RequestParam String value, Long time) {
        CacheKeyBuilder builder = new CacheKeyBuilder() {
            @Override
            public String getPrefix() {
                return DEF_KEY;
            }

            @Override
            public Duration getExpire() {
                return Duration.ofSeconds(5L);
            }
        };
        CacheKey cacheKey = builder.key(key);
        cacheOps.set(cacheKey, value);
        return R.success();
    }

    @GetMapping("/exists")
    public R exists(@RequestParam("key") String key) {
        CacheKeyBuilder builder = () -> DEF_KEY;
        CacheKey cacheKey = builder.key(key);
        return R.success(cacheOps.exists(cacheKey));
    }


}
