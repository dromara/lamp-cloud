package com.github.zuihou.authority.config;

import com.github.zuihou.authority.service.common.OptLogService;
import com.github.zuihou.common.config.BaseConfig;
import com.github.zuihou.log.event.SysLogListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zuihou
 * @createTime 2017-12-15 14:42
 */
@Configuration
public class AuthorityWebConfiguration extends BaseConfig {
    @Value("${zuihou.database.bizDatabase:zuihou_defaults}")
    private String database;

    @Bean
    public SysLogListener sysLogListener(OptLogService optLogService) {
        return new SysLogListener(this.database, (log) -> optLogService.save(log));
    }
}
