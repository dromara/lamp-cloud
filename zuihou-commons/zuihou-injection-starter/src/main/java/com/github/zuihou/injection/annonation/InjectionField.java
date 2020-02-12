package com.github.zuihou.injection.annonation;

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
public @interface InjectionField {
    /**
     * 固定查询值
     *
     * @return
     */
    String key() default "";

    /**
     * 执行查询任务的类
     * <p/>
     * api()  和 feign() 任选其一,  使用 api时，请填写实现类， 使用feign时，填写接口即可
     * 如： @InjectionField(api="userServiceImpl") 等价于 @InjectionField(feign=UserService.class)
     * 如： @InjectionField(api="userController") 等价于 @InjectionField(feign=UserApi.class)
     * <p>
     * 注意：若使用feignclient调用， 则一定要加上 @FeignClient(qualifier = "xxxApi"), 否则会注入失败
     *
     * @return
     */
    String api() default "";

    /**
     * 执行查询任务的类
     * <p/>
     * api()  和 feign() 任选其一,  使用 api时，请填写实现类， 使用feign时，填写接口即可
     * 如： @InjectionField(api="userServiceImpl") 等价于 @InjectionField(feign=UserService.class)
     * 如： @InjectionField(api="userController") 等价于 @InjectionField(feign=UserApi.class)
     * <p>
     * 注意： 本系统中，最好不要使用feign 方式调用，否则 entity 会依赖 api 模块，造成依赖冲突！
     *
     * @return
     */
    Class<? extends Object> feign() default Object.class;

    /**
     * 目标类中的调用方法
     * <p>
     * 若 找不到 api(feign) + method，则忽略该字段
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
