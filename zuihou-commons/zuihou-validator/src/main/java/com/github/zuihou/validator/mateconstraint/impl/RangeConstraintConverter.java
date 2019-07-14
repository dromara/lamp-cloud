package com.github.zuihou.validator.mateconstraint.impl;


import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.Size;

import com.github.zuihou.validator.mateconstraint.IConstraintConverter;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;


public class RangeConstraintConverter extends BaseConstraintConverter implements IConstraintConverter {

    @Override
    protected List<String> getMethods() {
        return Arrays.asList("min", "max", "message");
    }

    @Override
    protected String getType(Class<? extends Annotation> type) {
        return "range";
    }

    @Override
    protected List<Class<? extends Annotation>> getSupport() {
        return Arrays.asList(Length.class, Size.class, Range.class);
    }

}
