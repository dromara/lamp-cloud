package com.github.zuihou.validator.mateconstraint.impl;


import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import com.github.zuihou.validator.mateconstraint.IConstraintConverter;

import org.hibernate.validator.constraints.URL;

/**
 * 正则校验规则
 *
 * @author wz
 */
public class RegExConstraintConverter extends BaseConstraintConverter implements IConstraintConverter {
    @Override
    protected String getType(Class<? extends Annotation> type) {
        return "regEx";
    }

    @Override
    protected List<Class<? extends Annotation>> getSupport() {
        return Arrays.asList(Pattern.class, Email.class, URL.class);
    }

    @Override
    protected List<String> getMethods() {
        return Arrays.asList("regexp", "message");
    }
}
