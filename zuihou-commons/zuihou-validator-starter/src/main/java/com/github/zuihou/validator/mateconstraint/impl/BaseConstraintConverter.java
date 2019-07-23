package com.github.zuihou.validator.mateconstraint.impl;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.zuihou.validator.mateconstraint.IConstraintConverter;
import com.github.zuihou.validator.model.ConstraintInfo;

/**
 * 约束提取基础类
 *
 * @author zuihou
 * @date 2019-07-14 12:13
 */
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
            if (value != null && value instanceof String && ((String) value).contains("{")) {
                attr.put(method, "");
            } else {
                attr.put(method, value);
            }
        }
        return new ConstraintInfo().setType(getType(ano.annotationType())).setAttrs(attr);
    }


    /**
     * 子类返回各自具体支持的验证注解 类型
     *
     * @param
     * @return
     * @author zuihou
     * @date 2019-07-14 12:14
     */
    protected abstract List<Class<? extends Annotation>> getSupport();


    /**
     * 子类返回需要反射的验证注解的 字段值
     *
     * @return
     */
    protected List<String> getMethods() {
        return methods;
    }


    /**
     * 子类返回自定义的类型
     *
     * @param type
     * @return
     */
    protected abstract String getType(Class<? extends Annotation> type);


}
