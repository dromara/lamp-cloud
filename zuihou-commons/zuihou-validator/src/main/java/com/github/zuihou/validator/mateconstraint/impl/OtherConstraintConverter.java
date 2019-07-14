package com.github.zuihou.validator.mateconstraint.impl;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.github.zuihou.validator.mateconstraint.IConstraintConverter;


public class OtherConstraintConverter extends BaseConstraintConverter implements IConstraintConverter {


    @Override
    protected String getType(Class<? extends Annotation> type) {
        return type.getSimpleName();
    }

    @Override
    protected List<Class<? extends Annotation>> getSupport() {
        return new ArrayList<>();
    }


    @Override
    protected List<String> getMethods() {
        return Arrays.asList("message");
    }
}
