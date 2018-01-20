package com.github.zuihou.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * mybatis 基础操作类
 * @auther Created by zuihou on 2016-08-01 14:18.
 */
public abstract class BaseExample<T> implements Serializable {
    private final static Logger LOGGER = LoggerFactory.getLogger(BaseExample.class);

    private static final long serialVersionUID = 1L;
    protected String orderByClause;

    protected String groupByClause;

    protected String limitValue;

    protected boolean distinct;

    protected List<T> oredCriteria;
    private Class<T> persistentClass;


    /**
     * @mbggenerated
     */
    @SuppressWarnings("unchecked")
    public BaseExample() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        oredCriteria = new ArrayList<>();
    }

    /**
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * @mbggenerated
     */
    public String getGroupByClause() {
        return groupByClause;
    }

    /**
     * @mbggenerated
     */
    public void setGroupByClause(String groupByClause) {
        this.groupByClause = groupByClause;
    }

    /**
     * @mbggenerated
     */
    public String getLimitValue() {
        return limitValue;
    }

    /**
     * @mbggenerated
     */
    public void setLimitValue(String limitValue) {
        this.limitValue = limitValue;
    }

    /**
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * @mbggenerated
     */
    public List<T> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * @mbggenerated
     */
    public void or(T criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * @mbggenerated
     */
    public T or() {
        T criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * @mbggenerated
     */
    public T createCriteria() {
        T criteria = createCriteriaInternal();
        if (oredCriteria.isEmpty()) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }


    /**
     * @mbggenerated
     */
    protected T createCriteriaInternal() {
        T criteria = null;
        try {
            criteria = persistentClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            LOGGER.error("BaseExample T instance error", e);
        }
        return criteria;
    }

    /**
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    private final static String LIKE = "%";

    public static String fullLike(String value) {
        if (value != null && !value.isEmpty()) {
            return LIKE + value + LIKE;
        }
        return null;
    }

    public static String leftLike(String value) {
        if (value != null && !value.isEmpty()) {
            return value + LIKE;
        }
        return null;
    }

    public static String rightLike(String value) {
        if (value != null && !value.isEmpty()) {
            return LIKE + value;
        }
        return null;
    }


    public static class Criterion implements Serializable {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public Criterion() {
            super();
        }

        public Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        public Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        public Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        public Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        public Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }
    }
}
