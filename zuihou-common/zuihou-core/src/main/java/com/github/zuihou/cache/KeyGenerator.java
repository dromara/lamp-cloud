package com.github.zuihou.cache;

import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author zuihou
 * @createTime 2017-12-26 14:40
 */
public class KeyGenerator {
    @Value("${spring.redis.key-prefix:dev}")
    private String keyPrefix;
    @Value("${spring.application.name:null}")
    private String bizModular;

    /**
     * key 前缀
     *
     * @return
     */
    public String keyPrefix() {
        return keyPrefix;
    }

    /**
     * 业务系统
     *
     * @return
     */
    public String bizModular() {
        return bizModular;
    }

}
