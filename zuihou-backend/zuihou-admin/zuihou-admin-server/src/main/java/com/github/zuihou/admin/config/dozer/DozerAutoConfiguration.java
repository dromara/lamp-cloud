package com.github.zuihou.admin.config.dozer;


import org.dozer.Mapper;
import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;


/**
 * Dozer spring auto configuration.
 * <p>
 * ConditionalOnClass：该注解的参数对应的类必须存在，否则不解析该注解修饰的配置类；
 * ConditionalOnMissingBean：该注解表示，如果存在它修饰的类的bean，则不需要再创建这个bean；
 *
 * @author zuihou
 * @createTime 2017-11-23 16:27
 * @see http://dozer.sourceforge.net/documentation/usage.html
 * @see http://www.jianshu.com/p/bf8f0e8aee23
 */
@Configuration
@ConditionalOnClass({DozerBeanMapperFactoryBean.class, Mapper.class})
@ConditionalOnMissingBean(Mapper.class)
@EnableConfigurationProperties(DozerConfigurationProperties.class)
public class DozerAutoConfiguration {

    private final DozerConfigurationProperties configurationProperties;

    /**
     * Constructor for creating auto configuration.
     *
     * @param configurationProperties properties
     */
    @Autowired
    public DozerAutoConfiguration(DozerConfigurationProperties configurationProperties) {
        this.configurationProperties = configurationProperties;
    }

    /**
     * Creates default Dozer mapper
     *
     * @return Dozer mapper
     * @throws IOException if there is an exception during initialization.
     */
    @Bean
    public DozerBeanMapperFactoryBean dozerMapper() throws IOException {
        DozerBeanMapperFactoryBean factoryBean = new DozerBeanMapperFactoryBean();
        factoryBean.setMappingFiles(configurationProperties.getMappingFiles());
        return factoryBean;
    }
}
