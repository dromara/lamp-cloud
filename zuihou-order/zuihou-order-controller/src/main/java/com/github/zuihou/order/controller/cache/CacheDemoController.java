//package com.github.zuihou.order.controller.cache;
//
//import com.github.zuihou.base.R;
//import com.github.zuihou.cache.repository.CacheRepository;
//import io.swagger.annotations.Api;
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @author zuihou
// * @date 2020/6/7 下午9:20
// */
//@Slf4j
//@RestController
//@RequestMapping("/cache")
//@Api(value = "Cache", tags = "缓存演示")
//@Setter
//public class CacheDemoController {
//
//    @Autowired
//    private CacheRepository cacheRepository;
//
//
//    @GetMapping("/get")
//    public R get(@RequestParam("key") String key) {
//        return R.success(cacheRepository.get(key));
//    }
//
//    @GetMapping("/getOrDef")
//    public R getOrDef(@RequestParam("key") String key, @RequestParam(value = "def", required = false) String def) {
//        return R.success(cacheRepository.getOrDef(key, (k) -> def));
//    }
//
//    @GetMapping("/del")
//    public R del(@RequestParam("keys") String[] keys) {
//        return R.success(cacheRepository.del(keys));
//    }
//
//    @GetMapping("/flushDb")
//    public R flushDb() {
//        cacheRepository.flushDb();
//        return R.success();
//    }
//
//    @GetMapping("/set")
//    public R set(@RequestParam("key") String key, @RequestParam String value) {
////        cacheRepository.set(key, new Order().setName(value).setCreateTime(LocalDateTime.now()).setId(123L));
//        cacheRepository.set(key, value);
//        return R.success();
//    }
//
//    @GetMapping("/setExpire")
//    public R setExpire(@RequestParam("key") String key, @RequestParam String value, Long time) {
//        cacheRepository.setExpire(key, value, time);
//        return R.success();
//    }
//
//    @GetMapping("/exists")
//    public R exists(@RequestParam("key") String key) {
//        return R.success(cacheRepository.exists(key));
//    }
//
//
//}
