package com.github.zuihou.example;


import com.github.zuihou.example.BaseExample.Criterion;

import java.util.ArrayList;
import java.util.List;


/**
 * @author zuihou
 * @createTime 2017-12-08 11:36
 */
public abstract class BaseGeneratedCriteria {
    protected List<Criterion> criteria;

    public BaseGeneratedCriteria() {
        super();
        criteria = new ArrayList<BaseExample.Criterion>();
    }

    public boolean isValid() {
        return criteria.size() > 0;
    }

    public List<Criterion> getAllCriteria() {
        return criteria;
    }

    public List<Criterion> getCriteria() {
        return criteria;
    }

    protected void addCriterion(String condition) {
        if (condition == null || condition.isEmpty()) {
            return;
        }
        criteria.add(new Criterion(condition));
    }

    protected void addCriterion(String condition, Object value, String property) {
        if (value == null) {
            return;
        }
        if (value instanceof String && ((String) value).isEmpty()) {
            return;
        }
        criteria.add(new Criterion(condition, value));
    }

    protected void addCriterion(String condition, Object value1, Object value2, String property) {
        if (value1 == null || value2 == null) {
            return;
        }
        if (value1 instanceof String && ((String) value1).isEmpty()) {
            return;
        }
        if (value2 instanceof String && ((String) value2).isEmpty()) {
            return;
        }
        criteria.add(new Criterion(condition, value1, value2));
    }

}
