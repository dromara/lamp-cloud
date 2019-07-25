package com.github.zuihou.openfeign;

import org.springframework.context.annotation.Bean;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2019/07/25
 */
public class OpenFeignAutoConfiguration {
    /**
     * 在feign调用方配置， 解决入参和出参是 date 类型
     *
     * @return
     */
    @Bean
    public DateFormatRegister dateFormatRegister() {
        return new DateFormatRegister();
    }
}
