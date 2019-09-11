//package com.github.zuihou.general.controller;
//
//import java.time.LocalDateTime;
//
//import com.github.zuihou.authority.entity.auth.User;
//import com.github.zuihou.authority.enumeration.auth.Sex;
//import com.github.zuihou.base.R;
//
//import io.swagger.annotations.Api;
//import lombok.extern.slf4j.Slf4j;
//import net.oschina.j2cache.CacheChannel;
//import net.oschina.j2cache.CacheObject;
//import net.oschina.j2cache.J2Cache;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * 通用 控制器
// *
// * @author zuihou
// * @date 2019/07/25
// */
//@Slf4j
//@RestController
//@Api(value = "abcde", tags = "abcde")
//public class AbcdeController {
//
//    @GetMapping("/set")
//    public R<Object> set(String region, String key) {
//        CacheChannel cache = J2Cache.getChannel();
//        cache.set(region, key, User.builder().id(Long.MAX_VALUE).account(key).name("她最好").sex(Sex.M).createTime(LocalDateTime.now()).build());
//
//        return R.success(region + key);
//    }
//
//    @GetMapping("/get")
//    public R<Object> get(String region, String key) {
//        CacheChannel cache = J2Cache.getChannel();
//        int check = cache.check(region, key);
//        log.info("check={}", check);
//        if (check > 0) {
//            CacheObject cacheObject = cache.get(region, key);
//            System.out.println(cacheObject);
//            return R.success(cacheObject);
//        }
//
//        return R.success(check);
//
//    }
//
//    @GetMapping("/evict")
//    public R<Object> evict(String region, String key) {
//        CacheChannel cache = J2Cache.getChannel();
//        cache.evict(region, key);
//
//        return R.success(region + key);
//    }
//}
