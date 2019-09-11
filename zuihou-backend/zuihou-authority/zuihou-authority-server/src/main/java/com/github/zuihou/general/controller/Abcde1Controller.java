//package com.github.zuihou.general.controller;//package com.github.zuihou.general.controller;
//
//import java.time.LocalDateTime;
//
//import com.alicp.jetcache.Cache;
//import com.alicp.jetcache.anno.CreateCache;
//import com.github.zuihou.authority.entity.auth.User;
//import com.github.zuihou.authority.enumeration.auth.Sex;
//import com.github.zuihou.base.R;
//
//import io.swagger.annotations.Api;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
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
//@Api(value = "abcde1", tags = "abcde1")
//public class Abcde1Controller {
//    @CreateCache(name = "myServiceCache:")
//    private Cache<String, User> cache;
//    String key = "jetcache";
//
//    @GetMapping("/put")
//    public R<Object> put(@RequestParam(value = "value", required = false) String value) {
//
//        cache.put(key, User.builder().id(Long.MAX_VALUE).account("zuihou").name("她最好").sex(Sex.M).createTime(LocalDateTime.now()).build());
//        log.info("value={}", value);
//
//        return R.success(null);
//    }
//
//    @GetMapping("/get")
//    public R<Object> get(@RequestParam(value = "value", required = false) String value) {
//
//        User value2 = cache.get(key);
//        log.info("value={}", value2);
//
//        return R.success(null);
//    }
//
//    @GetMapping("/evict")
//    public R<Object> evict(@RequestParam(value = "value", required = false) String value) {
//
//        cache.remove(key);
//
//        return R.success(null);
//    }
//}
