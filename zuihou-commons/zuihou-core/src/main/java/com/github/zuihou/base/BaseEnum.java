package com.github.zuihou.base;

import java.util.Arrays;
import java.util.Map;

import com.github.zuihou.utils.MapHelper;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2019/07/26
 */
public interface BaseEnum {
    /**
     * 将制定的枚举集合转成 map
     * key -> name
     * value -> desc
     *
     * @param list
     * @return
     */
    static Map<String, String> getMap(BaseEnum[] list) {
        return MapHelper.uniqueIndex(Arrays.asList(list), BaseEnum::getCode, BaseEnum::getDesc);
    }


    /**
     * 编码重写
     *
     * @return
     */
    default String getCode() {
        return toString();
    }

    /**
     * 描述
     *
     * @return
     */
    String getDesc();
}
