package com.github.zuihou.sms.controller;

import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.cache.repository.CacheRepository;
import com.github.zuihou.lock.DistributedLock;
import com.github.zuihou.sms.entity.SmsProvider;
import com.github.zuihou.sms.enumeration.ProviderType;
import com.github.zuihou.sms.service.SmsProviderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试类
 *
 * @author zuihou
 * @date 2019/09/02
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/test")
@Api(value = "test", tags = "测试")
@RefreshScope
public class TestController extends BaseController {
    @Autowired
    private CacheRepository redisRepository;
    @Autowired
    private DistributedLock distributedLock;
    private String keyPre = "provider";

    @Autowired
    private SmsProviderService smsProviderService;

    @Value("${zuihou.common:local}")
    private String common;
    @Value("${zuihou.server:local}")
    private String server;
    @Value("${zuihou.dev:local}")
    private String dev;
    @Value("${zuihou.docker:local}")
    private String docker;

    @ApiOperation(value = "测试", notes = "测试")
    @PostMapping(value = "/anno/zuihou")
    public R<String> test() {
        return success("common=" + common + ",server=" + server + ",dev=" + dev + ",docker=" + docker);
    }


    @GetMapping("get1")
    public R<Object> get1(Long id, Long sleep) {

        String key = keyPre + ":" + id;

        redisRepository.getOrDef(key, (k) ->
                smsProviderService.getById(id)
        );

        SmsProvider provider = redisRepository.get(key);
        if (provider == null) {
//            boolean lock = distributedLock.lock(key + "lock");
//            if (lock) {
            SmsProvider providerDb = smsProviderService.getById(id);
            if (providerDb != null) {
                redisRepository.set(key, providerDb);
            }
//                distributedLock.releaseLock(key + "lock");
//            }
        }

        return success(provider);
    }

    @GetMapping("update2")
    public R<Object> update2(Long id, Long sleep) {
        String key = keyPre + ":" + id;
//        boolean lock = distributedLock.lock(key + "lock");

//        if (lock) {

        SmsProvider provider = SmsProvider.builder()
                .id(id)
                .appId("123").appSecret("123")
                .providerType(ProviderType.ALI)
                .build();
        smsProviderService.updateById(provider);

        try {
            Thread.sleep(sleep);
        } catch (Exception e) {

        }

        //这里报错就会出现 缓存不一致的情况
        redisRepository.del(key);
//            distributedLock.releaseLock(key + "lock");
//        }


        return success(key);
    }
}
