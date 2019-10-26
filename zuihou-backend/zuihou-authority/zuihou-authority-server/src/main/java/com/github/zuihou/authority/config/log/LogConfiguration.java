package com.github.zuihou.authority.config.log;

import com.github.zuihou.authority.service.common.OptLogService;
import com.github.zuihou.log.event.SysLogListener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 日志自动配置
 *
 * @author zuihou
 * @date 2019/07/02
 */
@EnableAsync
@Configuration
public class LogConfiguration {

    @Value("${zuihou.mysql.biz-database:zuihou_defaults}")
    private String database;

    @Bean
    public SysLogListener sysLogListener(OptLogService optLogService) {
        return new SysLogListener(database, (log) -> optLogService.save(log));
    }
}
