package com.github.zuihou.remotedata.annonation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 远程查询字段
 * <p>
 * 强烈建议：不要对象之间互相依赖
 * 如： User 想要注入 File， File也想注入User
 *
 * @author zuihou
 * @create 2020年01月18日17:59:25
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})
public @interface RemoteField {
    /**
     * 查询值
     *
     * @return
     */
    String key() default "";

    /**
     * 目标类
     *
     * @return
     */
    String api() default "";

    /**
     * 目标类中的调用方法
     * <p>
     * 若 找不到 api + method，则忽略该字段
     *
     * @return
     */
    String method() default "findByRemote";

    /**
     * 是否以属性值合并作为查询值
     *
     * @return
     */
    @Deprecated
    boolean isValueNeedMerge() default false;

    /**
     * 最大递归深度
     * 防止 A、B对象 互相注入出现死循环
     * 默认值 3 (事不过三~)
     *
     * @return
     */
    int depth() default 3;

}
