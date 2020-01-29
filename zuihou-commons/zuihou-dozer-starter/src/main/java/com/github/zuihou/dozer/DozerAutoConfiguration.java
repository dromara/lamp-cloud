package com.github.zuihou.dozer;


import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.spring.DozerBeanMapperFactoryBean;
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
 * <p>
 * http://dozer.sourceforge.net/documentation/usage.html
 * http://www.jianshu.com/p/bf8f0e8aee23
 *
 * @author zuihou
 * @createTime 2017-11-23 16:27
 */
@Configuration
@ConditionalOnClass({DozerBeanMapperFactoryBean.class, Mapper.class})
@ConditionalOnMissingBean(Mapper.class)
@EnableConfigurationProperties(DozerProperties.class)
public class DozerAutoConfiguration {
    @Bean
    public DozerUtils getDozerUtils(Mapper mapper) {
        return new DozerUtils(mapper);
    }


    private final DozerProperties properties;

    /**
     * Constructor for creating auto configuration.
     *
     * @param properties properties
     */
    public DozerAutoConfiguration(DozerProperties properties) {
        this.properties = properties;
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
        // 官方这样子写，没法用 匹配符！
        //factoryBean.setMappingFiles(properties.getMappingFiles());
        factoryBean.setMappingFiles(properties.resolveMapperLocations());
        return factoryBean;
    }
}
