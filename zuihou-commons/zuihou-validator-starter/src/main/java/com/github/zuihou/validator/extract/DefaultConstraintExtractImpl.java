package com.github.zuihou.validator.extract;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Validator;

import com.github.zuihou.validator.mateconstraint.IConstraintConverter;
import com.github.zuihou.validator.mateconstraint.impl.NotNullConstraintConverter;
import com.github.zuihou.validator.mateconstraint.impl.OtherConstraintConverter;
import com.github.zuihou.validator.mateconstraint.impl.RangeConstraintConverter;
import com.github.zuihou.validator.mateconstraint.impl.RegExConstraintConverter;
import com.github.zuihou.validator.model.ConstraintInfo;
import com.github.zuihou.validator.model.FieldValidatorDesc;
import com.github.zuihou.validator.model.ValidConstraint;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.ValidatorImpl;
import org.hibernate.validator.internal.metadata.BeanMetaDataManager;
import org.hibernate.validator.internal.metadata.aggregated.BeanMetaData;
import org.hibernate.validator.internal.metadata.core.MetaConstraint;
import org.hibernate.validator.internal.metadata.location.ConstraintLocation;

/**
 * 缺省的约束提取器
 *
 * @author zuihou
 * @date 2019-07-14 12:12
 */
@Slf4j
public class DefaultConstraintExtractImpl implements IConstraintExtract {

    private Validator validator;
    private BeanMetaDataManager beanMetaDataManager;
    private List<IConstraintConverter> constraintConverters;

    public DefaultConstraintExtractImpl(Validator validator) {
        this.validator = validator;
        init();
    }

    public void init() {
        try {
            Field beanMetaDataManagerField = ValidatorImpl.class.getDeclaredField("beanMetaDataManager");
            beanMetaDataManagerField.setAccessible(true);
            beanMetaDataManager = (BeanMetaDataManager) beanMetaDataManagerField.get(validator);
            constraintConverters = new ArrayList<>(4);
            constraintConverters.add(new NotNullConstraintConverter());
            constraintConverters.add(new RangeConstraintConverter());
            constraintConverters.add(new RegExConstraintConverter());
            constraintConverters.add(new OtherConstraintConverter());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            log.error("初始化验证器失败", e);
        }
    }

    @Override
    public Collection<FieldValidatorDesc> extract(List<ValidConstraint> constraints) throws Exception {
        if (constraints == null || constraints.size() == 0) {
            return Collections.EMPTY_LIST;
        }
        Map<String, FieldValidatorDesc> fieldValidatorDesc = new HashMap();
        for (ValidConstraint constraint : constraints) {
            doExtract(constraint, fieldValidatorDesc);
        }

        return fieldValidatorDesc.values();
    }

    private void doExtract(ValidConstraint constraint, Map<String, FieldValidatorDesc> fieldValidatorDesc) throws Exception {

        Class<?> targetClazz = constraint.getTarget();
        Class<?>[] groups = constraint.getGroups();

        //测试一下这个方法
        //validator.getConstraintsForClass(targetClazz).getConstrainedProperties()

        BeanMetaData<?> res = beanMetaDataManager.getBeanMetaData(targetClazz);
        Set<MetaConstraint<?>> r = res.getMetaConstraints();
        for (MetaConstraint<?> metaConstraint : r) {
            builderFieldValidatorDesc(metaConstraint, groups, fieldValidatorDesc);
        }
    }


    private void builderFieldValidatorDesc(MetaConstraint<?> metaConstraint, Class<?>[] groups,
                                           Map<String, FieldValidatorDesc> fieldValidatorDescs) throws Exception {
        //字段上的组
        Set<Class<?>> groupsMeta = metaConstraint.getGroupList();
        boolean isContainsGroup = false;

        //需要验证的组
        for (Class<?> group : groups) {
            if (groupsMeta.contains(group)) {
                isContainsGroup = true;
                break;
            }
            for (Class<?> g : groupsMeta) {
                if (g.isAssignableFrom(group)) {
                    isContainsGroup = true;
                    break;
                }
            }
        }
        if (!isContainsGroup) {
            return;
        }

        ConstraintLocation con = metaConstraint.getLocation();
        String domainName = con.getDeclaringClass().getSimpleName().toLowerCase();
        String fieldName = con.getMember().getName();
        String key = domainName + fieldName;
        FieldValidatorDesc desc = fieldValidatorDescs.get(domainName + fieldName);
        if (desc == null) {
            desc = new FieldValidatorDesc();
            desc.setDomainName(domainName);
            desc.setFieldName(fieldName);
            desc.setFieldType(getType(((Field) con.getMember()).getType()));
            desc.setConstraints(new ArrayList<>(5));
            fieldValidatorDescs.put(key, desc);
        }
        ConstraintInfo constraint = builderConstraint(metaConstraint.getDescriptor().getAnnotation());
        desc.getConstraints().add(constraint);
    }


    private String getType(Class<?> declaringClass) {

        if (Double.class.isAssignableFrom(declaringClass) || Float.class.isAssignableFrom(declaringClass)) {
            return "double";
        }
        if (Number.class.isAssignableFrom(declaringClass)) {
            return "int";
        }
        if (Date.class.isAssignableFrom(declaringClass)) {
            return "date";
        }
        if (CharSequence.class.isAssignableFrom(declaringClass)) {
            return "string";
        }
        return declaringClass.getSimpleName().toLowerCase();
    }

    private ConstraintInfo builderConstraint(Annotation annotation) throws Exception {
        for (IConstraintConverter constraintConverter : constraintConverters) {
            if (constraintConverter.support(annotation.annotationType())) {
                return constraintConverter.converter(annotation);
            }
        }
        return null;
    }
}
