package ${pg.parent}.${moduleName}.config;

<#list mybatisConfigurationImport as pkg>
import ${pkg};
</#list>
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置一些 Mybatis 常用重用拦截器
 * ${pg.description}
 *
 * @author ${pg.author}
 * @date ${datetime}
 */
@Configuration
@Slf4j
@EnableConfigurationProperties({DatabaseProperties.class})
<#if multiTenantType == MULTI_TENANT_TYPE_NONE>
@MapperScan(basePackages = { "${pg.parent}", "${pg.utilParent}" }, annotationClass = Repository.class)
</#if>
<#if multiTenantType == MULTI_TENANT_TYPE_COLUMN>
public class ${serviceNameUpper}MybatisConfiguration extends ColumnMybatisConfiguration {
<#else>
public class ${serviceNameUpper}MybatisConfiguration extends BaseMybatisConfiguration {
</#if>

    public ${serviceNameUpper}MybatisConfiguration(DatabaseProperties databaseProperties) {
        super(databaseProperties);
    }

    @Bean
    public DataScopeInnerInterceptor getDataScopeInnerInterceptor() {
        return new DataScopeInnerInterceptor();
    }


    /**
     * 数据权限插件
     * @return 数据权限插件
     */
    @Override
    protected List<InnerInterceptor> getPaginationBeforeInnerInterceptor() {
        List<InnerInterceptor> list = new ArrayList<>();
        Boolean isDataScope = databaseProperties.getIsDataScope();
        if (isDataScope) {
            list.add(getDataScopeInnerInterceptor());
        }
        return list;
    }

}
