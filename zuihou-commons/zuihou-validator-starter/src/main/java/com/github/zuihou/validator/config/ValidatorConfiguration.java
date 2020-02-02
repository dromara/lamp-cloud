package com.github.zuihou.validator.config;

import com.github.zuihou.validator.constraintvalidators.LengthConstraintValidator;
import com.github.zuihou.validator.constraintvalidators.NotEmptyConstraintValidator;
import com.github.zuihou.validator.constraintvalidators.NotNullConstraintValidator;
import com.github.zuihou.validator.controller.FormValidatorController;
import com.github.zuihou.validator.extract.DefaultConstraintExtractImpl;
import com.github.zuihou.validator.extract.IConstraintExtract;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.hibernate.validator.cfg.ConstraintMapping;
import org.hibernate.validator.cfg.context.ConstraintDefinitionContext;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.internal.cfg.context.DefaultConstraintMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.validation.Configuration;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 验证器配置
 *
 * @author zuihou
 * @date 2019/07/14
 */
public class ValidatorConfiguration {

    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = warp(Validation.byProvider(HibernateValidator.class)
                .configure()
                //快速失败返回模式
                .addProperty("hibernate.validator.fail_fast", "true"))
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }

    private Configuration<HibernateValidatorConfiguration> warp(HibernateValidatorConfiguration configuration) {
        addValidatorMapping(configuration);
        //其他操作
        return configuration;
    }

    private void addValidatorMapping(HibernateValidatorConfiguration configuration) {
        //增加一个我们自定义的校验处理器与length的映射
        ConstraintMapping mapping = new DefaultConstraintMapping();
        ConstraintDefinitionContext<Length> length = mapping.constraintDefinition(Length.class);
        length.includeExistingValidators(true);
        length.validatedBy(LengthConstraintValidator.class);

        ConstraintDefinitionContext<NotNull> notNull = mapping.constraintDefinition(NotNull.class);
        notNull.includeExistingValidators(true);
        notNull.validatedBy(NotNullConstraintValidator.class);

        ConstraintDefinitionContext<NotEmpty> notEmpty = mapping.constraintDefinition(NotEmpty.class);
        notEmpty.includeExistingValidators(true);
        notEmpty.validatedBy(NotEmptyConstraintValidator.class);

        configuration.addMapping(mapping);
    }

    /**
     * Method:  开启快速返回
     * Description:
     * 如果参数校验有异常，直接抛异常，不会进入到 controller，使用全局异常拦截进行拦截
     * Author: liu kai
     * Date: 2018/7/12 17:33
     *
     * @param
     * @return org.springframework.validation.beanvalidation.MethodValidationPostProcessor
     */
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(Validator validator) {
        MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
        /**设置validator模式为快速失败返回*/
        postProcessor.setValidator(validator);
        return postProcessor;
    }

    @Bean
    public IConstraintExtract constraintExtract(Validator validator) {
        return new DefaultConstraintExtractImpl(validator);
    }

    @Bean
    public FormValidatorController getFormValidatorController(IConstraintExtract constraintExtract, RequestMappingHandlerMapping requestMappingHandlerMapping) {
        return new FormValidatorController(constraintExtract, requestMappingHandlerMapping);
    }

}
