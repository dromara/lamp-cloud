package com.github.zuihou.validator.constraintvalidators;


import com.github.zuihou.base.validation.IValidatable;
import org.hibernate.validator.internal.constraintvalidators.bv.NotNullValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotNull;

/**
 * 自定义一个验证 NotNull 的校验器。自定义类需要实现IValidatable接口
 *
 * @author zuihou
 * @date 2020年02月02日20:59:21
 */
public class NotNullConstraintValidator implements ConstraintValidator<NotNull, IValidatable> {

    private NotNullValidator notNullValidator = new NotNullValidator();

    @Override
    public void initialize(NotNull parameters) {
        notNullValidator.initialize(parameters);
    }

    @Override
    public boolean isValid(IValidatable value, ConstraintValidatorContext constraintValidatorContext) {
        return value != null && value.value() != null;
    }
}
