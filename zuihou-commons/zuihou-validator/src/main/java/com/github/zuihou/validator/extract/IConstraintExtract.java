package com.github.zuihou.validator.extract;

import java.util.Collection;
import java.util.List;

import com.github.zuihou.validator.model.FieldValidatorDesc;
import com.github.zuihou.validator.model.ValidConstraint;


/**
 * 提取指定表单验证规则
 *
 * @author zuihou
 * @date 2019-06-12
 */
public interface IConstraintExtract {

    /**
     * 提取指定表单验证规则
     *
     * @param constraints
     * @return
     * @throws Exception
     */
    Collection<FieldValidatorDesc> extract(List<ValidConstraint> constraints) throws Exception;
}
