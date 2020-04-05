package com.github.zuihou.oauth.config;

import com.github.zuihou.authority.service.common.OptLogService;
import com.github.zuihou.boot.config.BaseConfig;
import com.github.zuihou.log.event.SysLogListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zuihou
 * @createTime 2017-12-15 14:42
 */
@Configuration
public class OauthWebConfiguration extends BaseConfig {

    /**
     * zuihou.log.enabled = true 并且 zuihou.log.type=DB时实例该类
     *
     * @param optLogService
     * @return
     */
    @Bean
    @ConditionalOnExpression("${zuihou.log.enabled:true} && 'DB'.equals('${zuihou.log.type:LOGGER}')")
    public SysLogListener sysLogListener(OptLogService optLogService) {
        return new SysLogListener((log) -> optLogService.save(log));
    }
}
