package com.github.zuihou.authority.config.dozer;


import com.github.dozermapper.core.Mapper;
import com.github.zuihou.common.utils.context.DozerUtils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Dozer spring auto configuration.
 * <p>
 * ConditionalOnClass：该注解的参数对应的类必须存在，否则不解析该注解修饰的配置类；
 * ConditionalOnMissingBean：该注解表示，如果存在它修饰的类的bean，则不需要再创建这个bean；
 *
 * @author tyh
 * @createTime 2017-11-23 16:27
 * @see http://dozer.sourceforge.net/documentation/usage.html
 * @see http://www.jianshu.com/p/bf8f0e8aee23
 */
@Configuration
public class DozerAutoConfiguration {
    @Bean
    public DozerUtils getDozerUtils(Mapper mapper) {
        return new DozerUtils(mapper);
    }
}
