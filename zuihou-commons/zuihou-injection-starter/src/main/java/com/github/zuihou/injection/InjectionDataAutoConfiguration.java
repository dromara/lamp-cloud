package com.github.zuihou.injection;

import com.github.zuihou.injection.aspect.InjectionResultAspect;
import com.github.zuihou.injection.configuration.InjectionProperties;
import com.github.zuihou.injection.core.InjectionCore;
import com.github.zuihou.injection.mybatis.typehandler.RemoteDataTypeHandler;
import com.github.zuihou.utils.SpringUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 关联字段数据注入工具 自动配置类
 *
 * @author zuihou
 * @date 2019/09/20
 */
@Slf4j
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(InjectionProperties.class)
@ConditionalOnProperty(name = "zuihou.injection.enabled", havingValue = "true", matchIfMissing = true)
public class InjectionDataAutoConfiguration {
    private InjectionProperties remoteProperties;

    /**
     * Spring 工具类
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public SpringUtils beanFactoryUtils() {
        return new SpringUtils();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = {"zuihou.injection.aop-enabled"}, havingValue = "true", matchIfMissing = true)
    public InjectionResultAspect getRemoteAspect(InjectionCore injectionCore) {
        return new InjectionResultAspect(injectionCore);
    }

    @Bean
    @ConditionalOnMissingBean
    public InjectionCore getInjectionCore() {
        return new InjectionCore(remoteProperties);
    }

    /**
     * Mybatis 类型处理器： 处理 RemoteData 类型的字段
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public RemoteDataTypeHandler getRemoteDataTypeHandler() {
        return new RemoteDataTypeHandler();
    }
}

