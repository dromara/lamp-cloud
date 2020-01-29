package com.github.zuihou.injection;

import com.github.zuihou.injection.aspect.InjectionResultAspect;
import com.github.zuihou.injection.configuration.InjectionProperties;
import com.github.zuihou.injection.core.InjectionCore;
import com.github.zuihou.injection.core.RemoteCore;
import com.github.zuihou.injection.facade.DefaultRemoteResultParser;
import com.github.zuihou.utils.SpringUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * zipkin 客服端自定义配置
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

    @Bean
    @ConditionalOnMissingBean
    public SpringUtils beanFactoryUtils() {
        return new SpringUtils();
    }

    @Bean
    @ConditionalOnMissingBean
    public RemoteCore mergeCore() {
        return new RemoteCore(remoteProperties);
    }

    @Bean
    public DefaultRemoteResultParser getDefaultRemoteResultParser() {
        return new DefaultRemoteResultParser();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = {"zuihou.injection.aop-enabled"}, havingValue = "true")
    public InjectionResultAspect getRemoteAspect(InjectionCore injectionCore) {
        return new InjectionResultAspect(injectionCore);
    }

    @Bean
    @ConditionalOnMissingBean
    public InjectionCore getInjectionCore() {
        return new InjectionCore();
    }

}
