package com.github.shiro.autoconfigure;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.web.filter.AccessControlFilter;

/**
 * Created by whf on 5/15/16.
 * @date 2019-07-23 11:59
 * @author zuihou
 */
public class ShiroFilterRegistry {
    private Map<String, AccessControlFilter> filterMap = new HashMap<>();


    public void addShiroFilter(String name, AccessControlFilter filter) {
        filterMap.put(name, filter);
    }

    public Map<String, AccessControlFilter> getFilterMap() {
        return filterMap;
    }
}
