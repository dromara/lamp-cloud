package com.github.zuihou.validator.model;

import java.util.List;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 字段校验规则信息
 *
 * @author zuihou
 * @date 2019-07-12 14:28
 */
@Data
@ToString
@Accessors(chain = true)
public class FieldValidatorDesc {

    /**
     * 字段名称
     */
    private String fieldName;
    /**
     * 字段所在的类名称短名称（也可以叫做领域名称）
     */
    private String domainName;
    /**
     * 字段的类型
     */
    private String fieldType;
    /**
     * 约束集合
     */
    private List<ConstraintInfo> constraints;

}
