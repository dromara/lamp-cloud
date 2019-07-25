package com.github.zuihou.validator.mateconstraint.impl;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.github.zuihou.validator.mateconstraint.IConstraintConverter;


/**
 * 非空 转换器
 *
 * @author zuihou
 * @date 2019-07-25 15:14
 */
public class NotNullConstraintConverter extends BaseConstraintConverter implements IConstraintConverter {


    @Override
    protected String getType(Class<? extends Annotation> type) {
        return "notNull";
    }

    @Override
    protected List<Class<? extends Annotation>> getSupport() {
        return Arrays.asList(NotNull.class, NotEmpty.class, NotBlank.class);
    }

    @Override
    protected List<String> getMethods() {
        return Arrays.asList("message");
    }
}
