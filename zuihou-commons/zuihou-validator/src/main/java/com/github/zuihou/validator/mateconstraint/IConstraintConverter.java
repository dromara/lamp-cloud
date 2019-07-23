package com.github.zuihou.validator.mateconstraint;

import java.lang.annotation.Annotation;

import com.github.zuihou.validator.model.ConstraintInfo;


/**
 * 约束转换器
 *
 * @author zuihou
 * @date 2019-07-14 12:13
 */
public interface IConstraintConverter {

    /**
     * 支持的类型
     *
     * @param clazz
     * @return
     */
    boolean support(Class<? extends Annotation> clazz);

    /**
     * 转换
     *
     * @param ano
     * @return
     * @throws Exception
     */
    ConstraintInfo converter(Annotation ano) throws Exception;
}
