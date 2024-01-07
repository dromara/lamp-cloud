package ${pg.parent}.${moduleName}.config;

<#list webConfigurationImport as pkg>
import ${pkg};
</#list>
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ${pg.description}-Web配置
 *
 * @author ${pg.author}
 * @date ${datetime}
 */
@Configuration
public class ${serviceNameUpper}WebConfiguration extends BaseConfig {

    /**
     * ${pg.projectPrefix}.log.enabled = true 并且 ${pg.projectPrefix}.log.type=DB时实例该类
     */
    @Bean
    @ConditionalOnExpression("${r'${'}${pg.projectPrefix}.log.enabled:true${r'}'} && 'DB'.equals('${r'${'}${pg.projectPrefix}.log.type:LOGGER${r'}'}')")
    public SysLogListener getSysLogListener(<#if pg.type == projectTypeCloud>LogApi<#else>BaseOperationLogService</#if> logApi) {
        return new SysLogListener(logApi::save);
    }
}
