package com.github.zuihou.authority.config.log;

import com.github.zuihou.authority.service.common.LogService;
import com.github.zuihou.log.event.SysLogListener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * This is a Description
 *
 * @author tangyh
 * @date 2019/07/02
 */
@EnableAsync
@Configuration
public class LogConfiguration {

    @Bean
    public SysLogListener sysLogListener(LogService logService) {
        return new SysLogListener((log) -> logService.save(log));
    }
}
