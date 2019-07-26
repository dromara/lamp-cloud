package com.github.zuihou.database.mybatis.conditions;


import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.database.mybatis.conditions.update.LbuWrapper;

/**
 * Wrappers 工具类， 该方法的主要目的是为了 缩短代码长度
 *
 * @author zuihou
 * @date 2019/06/14
 */
public class Wraps {

    private Wraps() {
        // ignore
    }

    /**
     * 获取 HyLambdaQueryWrapper&lt;T&gt;
     *
     * @param <T> 实体类泛型
     * @return LambdaQueryWrapper&lt;T&gt;
     */
    public static <T> LbqWrapper<T> lbQ() {
        return new LbqWrapper<>();
    }

    /**
     * 获取 HyLambdaQueryWrapper&lt;T&gt;
     *
     * @param entity 实体类
     * @param <T>    实体类泛型
     * @return LambdaQueryWrapper&lt;T&gt;
     */
    public static <T> LbqWrapper<T> lbQ(T entity) {
        return new LbqWrapper<>(entity);
    }

    /**
     * 获取 HyLambdaQueryWrapper&lt;T&gt;
     *
     * @param <T> 实体类泛型
     * @return LambdaUpdateWrapper&lt;T&gt;
     */
    public static <T> LbuWrapper<T> lbU() {
        return new LbuWrapper<>();
    }

    /**
     * 获取 HyLambdaQueryWrapper&lt;T&gt;
     *
     * @param entity 实体类
     * @param <T>    实体类泛型
     * @return LambdaUpdateWrapper&lt;T&gt;
     */
    public static <T> LbuWrapper<T> lbU(T entity) {
        return new LbuWrapper<>(entity);
    }

}
