//package com.github.zuihou;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Map;
//
//import com.github.zuihou.authority.entity.auth.User;
//import com.github.zuihou.authority.enumeration.auth.Sex;
//import com.github.zuihou.common.constant.CacheKey;
//import com.github.zuihou.redis.template.RedisRepository;
//
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.stereotype.Component;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
///**
// * redis测试类
// *
// * @author zuihou
// * @date 2019/08/06
// */
//
//@Component
//@Slf4j
//class CacheTest {
//    @Cacheable(value = CacheKey.REGISTER_USER, key = "#name")
//    public User getMenu(String name) {
//        log.info("name={}", name);
//        return User.builder()
//                .account(name).name("张三李四!@#$%^&*()_123")
//                .id(6079967614237410571L)
//                .sex(Sex.M)
//                .createTime(LocalDateTime.now())
//                .build();
//    }
//
//    @Cacheable(value = "zuihou", keyGenerator = "keyGenerator")
//    public User zuihou() {
//        return User.builder()
//                .account("zuihou").name("张三李四!@#$%^&*()_123")
//                .id(6079967614237410571L)
//                .sex(Sex.M)
//                .createTime(LocalDateTime.now())
//                .build();
//    }
//
//    @Cacheable(value = "test", key = "1")
//    public User test() {
//        return User.builder()
//                .account("zuihou").name("张三李四!@#$%^&*()_123")
//                .id(6079967614237410571L)
//                .sex(Sex.M)
//                .createTime(LocalDateTime.now())
//                .build();
//    }
//}
//
//@ComponentScan({"com.github.zuihou"})
//@SpringBootTest
//@RunWith(SpringJUnit4ClassRunner.class)
//@Slf4j
//public class RedisTest {
//    @Autowired
//    CacheTest cacheTest;
//    @Autowired
//    private RedisRepository redisRepository;
//
//    @Test
//    public void testCacheable() throws Exception {
//        log.info("user={}", cacheTest.getMenu("aaa"));
//        log.info("user={}", cacheTest.getMenu("aaa"));
//        log.info("user={}", cacheTest.getMenu("aaa"));
//        log.info("user={}", cacheTest.getMenu("aaa"));
//        log.info("user={}", cacheTest.getMenu("aaa"));
//        log.info("user={}", cacheTest.getMenu("bbb"));
//        log.info("user={}", cacheTest.getMenu("ccc"));
//        log.info("user={}", cacheTest.getMenu("ddd"));
//        log.info("user={}", cacheTest.getMenu("eee"));
//        log.info("zuihou={}", cacheTest.zuihou());
//        log.info("test={}", cacheTest.test());
//        log.info("end");
//
//        Thread.sleep(3000);
//
//        log.info("user={}", cacheTest.getMenu("aaa"));
//
//    }
//
//
//    @Test
//    public void testList() {
//        String key = "zuihoulist";
//        redisRepository.rightPush(key, 6079967614237410571L);
//        redisRepository.rightPush(key, 2079967614237410571L);
//        redisRepository.rightPush(key, 123L);
//        redisRepository.rightPush(key, 456L);
//
//        List<Long> list = redisRepository.getList(key, 0, 1);
//
//        list.forEach(val -> log.info("val={}", val));
//
//    }
//
//    /**
//     * ok
//     */
//    @Test
//    public void testHash2() {
//        String key = "zuihouhash";
//        Map<String, Object> hashValue = redisRepository.getHashValue(key);
//        hashValue.forEach((k, v) -> log.info("k={} v={}", k, v));
//    }
//
//    /**
//     * ok
//     */
//    @Test
//    public void testHash() {
//        String key = "zuihouhash";
//        String field1 = "张三";
//        field1 = "张三";
//        redisRepository.putHashValue(key, field1, "你好 redis");
//        String hello = redisRepository.getHashValues(key, field1);
//        log.info("hello={}", hello);
//
//        field1 = "lisi";
//        redisRepository.putHashValue(key, field1, 6079967614237410571L);
//        Long lisi = redisRepository.getHashValues(key, field1);
//        log.info("lisi={}", lisi);
//
//        User user = User.builder()
//                .account("admin").name("张三李四!@#$%^&*()_123")
//                .id(6079967614237410571L)
//                .sex(Sex.M)
//                .createTime(LocalDateTime.now())
//                .build();
//        field1 = "user";
//        redisRepository.putHashValue(key, field1, user);
//        User user2 = redisRepository.getHashValues(key, field1);
//        log.info("user={}", user2);
//
//
//    }
//
//    /**
//     * 成功
//     */
//    @Test
//    public void testSering() {
//        String key = "zuihou";
//        redisRepository.set(key, "你好 redis");
//        System.out.println((String) redisRepository.get(key));
//
//        redisRepository.set(key + "long", 6079967614237410571L);
//        Long lo = redisRepository.get(key + "long");
//        System.out.println(lo);
//
//        redisRepository.set(key + "int", 123);
//        Integer in = redisRepository.get(key + "int");
//        System.out.println(in);
//
//
//        User user = User.builder()
//                .account("admin").name("张三李四!@#$%^&*()_123")
//                .id(6079967614237410571L)
//                .build();
//        redisRepository.set(key + "obj", user);
//        User user2 = redisRepository.get(key + "obj");
//        System.out.println(user2);
//        System.out.println(user2.getId());
//
//    }
//
//
//}
