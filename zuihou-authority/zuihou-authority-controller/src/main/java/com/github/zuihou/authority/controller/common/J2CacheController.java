package com.github.zuihou.authority.controller.common;

import com.github.zuihou.base.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.CacheObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * j2cache 操作类
 *
 * @author zuihou
 * @date 2019/10/25
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/j2cache")
@Api(value = "J2Cache", tags = "缓存")
public class J2CacheController {
    @Autowired
    CacheChannel cache;

    @ApiOperation(value = "数据写入", notes = "数据写入")
    @GetMapping("/set")
    public R<Object> set(String region, String key, String value) {
        cache.set(region, key, value);
        return R.success(String.format("%s:%s", region, key));
    }

    @ApiOperation(value = "数据读取", notes = "数据读取")
    @GetMapping("/get")
    public R<Object> get(String region, String key) {
        CacheObject cacheObject = cache.get(region, key);
        return R.success(String.format("%s:%s====%s", region, key, cacheObject));
    }

    @ApiOperation(value = "淘汰缓存", notes = "淘汰缓存")
    @GetMapping("/evict")
    public R<Object> evict(String region, @RequestParam(value = "keys[]", required = false) String[] keys) {
        cache.evict(region, keys);
        return R.success(String.format("%s:%s", region, keys));
    }

    @ApiOperation(value = "检测存在那级缓存", notes = "检测存在那级缓存")
    @GetMapping("/check")
    public R<Object> check(String region, String key) {
        int check = cache.check(region, key);
        return R.success(String.format("%s:%s==%s", region, key, check));
    }

    @ApiOperation(value = "检测是否存在", notes = "检测是否存在")
    @GetMapping("/exists")
    public R<Object> exists(String region, String key) {
        boolean exists = cache.exists(region, key);
        return R.success(String.format("%s:%s==%s", region, key, exists));
    }

    @ApiOperation(value = "清理", notes = "清理")
    @GetMapping("/clear")
    public R<Object> clear(String region) {
        cache.clear(region);
        return R.success(String.format("%s", region));
    }

    @ApiOperation(value = "获取缓存的所有key", notes = "获取缓存的所有key")
    @GetMapping("/keys")
    public R<Collection<String>> keys(String region) {
        Collection<String> keys = cache.keys(region);
        return R.success(keys);
    }

    @Deprecated
    @ApiOperation(value = "慎用！获取所有的缓存！慎用！", notes = "慎用！获取所有的缓存！慎用！")
    @GetMapping("/regions")
    public R<Object> regions() {
        Collection<CacheChannel.Region> regions = cache.regions();
        return R.success(regions);
    }

    @ApiOperation(value = "删除1级缓存 Region", notes = "删除1级缓存 Region")
    @GetMapping("/removeRegion")
    public R<Object> regions(String region) {
        cache.removeRegion(region);
        return R.success(region);
    }

}
