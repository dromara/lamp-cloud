package com.github.zuihou.file.config.log;

import com.github.zuihou.authority.api.LogApi;
import com.github.zuihou.log.event.SysLogListener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2019/07/02
 */
@EnableAsync
@Configuration
public class LogConfiguration {

    @Bean
    public SysLogListener sysLogListener(LogApi logApi) {
        return new SysLogListener((log) -> logApi.save(log));
    }
}
