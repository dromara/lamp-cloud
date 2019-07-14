package com.github.zuihou.authority.config;

import com.github.zuihou.common.adapter.BaseConfig;
import com.github.zuihou.validator.extract.DefaultConstraintExtractImpl;
import com.github.zuihou.validator.extract.IConstraintExtract;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zuihou
 * @createTime 2017-12-15 14:42
 */
@Configuration
public class AuthorityConfiguration extends BaseConfig {

    /**
     * 覆盖父类的拦截器
     *
     * @return
     */
//    @Bean
//    @Override
//    public HandlerInterceptor getHandlerInterceptor() {
//        return new AuthClientContextHandlerInterceptor();
//    }
    @Bean
    public IConstraintExtract constraintExtract() throws Exception {
        return new DefaultConstraintExtractImpl(validator());
    }


//    @Bean
//    public FormValidatorController getFormValidatorController() {
//        return new FormValidatorController();
//    }
}
