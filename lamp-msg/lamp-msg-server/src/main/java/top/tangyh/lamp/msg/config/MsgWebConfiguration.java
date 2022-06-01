package top.tangyh.lamp.msg.config;

import top.tangyh.basic.boot.config.BaseConfig;
import top.tangyh.basic.log.event.SysLogListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.tangyh.lamp.common.api.LogApi;

/**
 * @author zuihou
 * @date 2017-12-15 14:42
 */
@Configuration
public class MsgWebConfiguration extends BaseConfig {
    /**
     * lamp.log.enabled = true 并且 lamp.log.type=DB时实例该类
     *
     * @param logApi 日志api
     * @return 监听
     */
    @Bean
    @ConditionalOnExpression("${lamp.log.enabled:true} && 'DB'.equals('${lamp.log.type:LOGGER}')")
    public SysLogListener sysLogListener(LogApi logApi) {
        return new SysLogListener(logApi::save);
    }
}
