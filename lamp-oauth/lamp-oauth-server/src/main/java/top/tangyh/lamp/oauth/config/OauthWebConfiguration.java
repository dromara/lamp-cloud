package top.tangyh.lamp.oauth.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import top.tangyh.basic.boot.config.BaseConfig;
import top.tangyh.basic.log.event.SysLogListener;
import top.tangyh.lamp.authority.service.common.OptLogService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.tangyh.lamp.common.properties.SystemProperties;

/**
 * @author zuihou
 * @date 2017-12-15 14:42
 */
@Configuration
@EnableConfigurationProperties(SystemProperties.class)
public class OauthWebConfiguration extends BaseConfig {

    /**
     * lamp.log.enabled = true 并且 lamp.log.type=DB时实例该类
     */
    @Bean
    @ConditionalOnExpression("${lamp.log.enabled:true} && 'DB'.equals('${lamp.log.type:LOGGER}')")
    public SysLogListener sysLogListener(OptLogService optLogService) {
        return new SysLogListener(optLogService::save);
    }
}
