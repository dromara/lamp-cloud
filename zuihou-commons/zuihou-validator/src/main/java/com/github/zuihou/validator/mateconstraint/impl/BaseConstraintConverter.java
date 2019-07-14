package com.github.zuihou.validator.mateconstraint.impl;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.zuihou.validator.mateconstraint.IConstraintConverter;
import com.github.zuihou.validator.model.ConstraintInfo;

public abstract class BaseConstraintConverter implements IConstraintConverter {


    private List<String> methods = Collections.EMPTY_LIST;

    @Override
    public boolean support(Class<? extends Annotation> clazz) {
        if (getSupport().isEmpty()) {
            return true;
        }
        return clazz != null && getSupport().contains(clazz);
    }

    @Override
    public ConstraintInfo converter(Annotation ano) throws Exception {
        Class<? extends Annotation> clazz = ano.getClass();
        Map<String, Object> attr = new HashMap<>(4);
        for (String method : getMethods()) {
            Object value = clazz.getMethod(method).invoke(ano);
            attr.put(method, value);
        }
        return new ConstraintInfo().setType(getType(ano.annotationType())).setAttrs(attr);
    }


    protected abstract List<Class<? extends Annotation>> getSupport();


    protected List<String> getMethods() {
        return methods;
    }


    protected abstract String getType(Class<? extends Annotation> type);


}
