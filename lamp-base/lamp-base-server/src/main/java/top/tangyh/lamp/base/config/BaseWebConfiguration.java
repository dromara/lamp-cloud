package top.tangyh.lamp.base.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.tangyh.basic.boot.config.BaseConfig;
import top.tangyh.basic.log.event.SysLogListener;
import top.tangyh.basic.utils.BeanPlusUtil;
import top.tangyh.lamp.base.service.system.BaseOperationLogService;
import top.tangyh.lamp.base.vo.save.system.BaseOperationLogSaveVO;

/**
 * 基础服务-Web配置
 *
 * @author zuihou
 * @date 2021-10-08
 */
@Configuration
public class BaseWebConfiguration extends BaseConfig {

    /**
     * lamp.log.enabled = true 并且 lamp.log.type=DB时实例该类
     */
    @Bean
    @ConditionalOnExpression("${lamp.log.enabled:true} && 'DB'.equals('${lamp.log.type:LOGGER}')")
    public SysLogListener sysLogListener(BaseOperationLogService logApi) {
        return new SysLogListener(data -> logApi.save(BeanPlusUtil.toBean(data, BaseOperationLogSaveVO.class)));
    }
}
