package com.github.zuihou.remotedata;

import com.github.zuihou.remotedata.aspect.RemoteAspect;
import com.github.zuihou.remotedata.configuration.RemoteProperties;
import com.github.zuihou.remotedata.core.InjectionCore;
import com.github.zuihou.remotedata.core.RemoteCore;
import com.github.zuihou.remotedata.facade.DefaultRemoteResultParser;
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
@EnableConfigurationProperties(RemoteProperties.class)
@ConditionalOnProperty(name = "zuihou.remote.enabled", havingValue = "true", matchIfMissing = true)
public class RemoteDataAutoConfiguration {
    private RemoteProperties remoteProperties;

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
//    @ConditionalOnProperty(name = "zuihou.remote.aopEnabled", havingValue = "true")
    public RemoteAspect getRemoteAspect(InjectionCore injectionCore) {
        return new RemoteAspect(injectionCore);
    }

    @Bean
    @ConditionalOnMissingBean
    public InjectionCore getInjectionCore() {
        return new InjectionCore();
    }

}
