package com.github.zuihou.dozer;


import com.github.dozermapper.core.Mapper;

import org.springframework.context.annotation.Bean;


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
public class DozerAutoConfiguration {
    @Bean
    public DozerUtils getDozerUtils(Mapper mapper) {
        return new DozerUtils(mapper);
    }
}
