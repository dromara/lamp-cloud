package com.github.zuihou.injection.core;

import com.github.zuihou.injection.annonation.InjectionField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"key"})
public class InjectionFieldPo {

    /**
     * 查询值
     *
     * @return
     */
    private String key;

    /**
     * 执行查询任务的类
     * <p/>
     * api()  和 feign() 任选其一,  使用 api时，请填写实现类， 使用feign时，填写接口即可
     * 如： @InjectionField(api="userServiceImpl") 等价于 @InjectionField(feign=UserService.class)
     * 如： @InjectionField(api="userController") 等价于 @InjectionField(feign=UserApi.class)
     *
     * @return
     */
    private String api;

    /**
     * 执行查询任务的类
     * <p/>
     * api()  和 feign() 任选其一,  使用 api时，请填写实现类， 使用feign时，填写接口即可
     * 如： @InjectionField(api="userServiceImpl") 等价于 @InjectionField(feign=UserService.class)
     * 如： @InjectionField(api="userController") 等价于 @InjectionField(feign=UserApi.class)
     */
    private Class<? extends Object> feign;

    /**
     * 调用方法
     *
     * @return
     */
    private String method;

    public InjectionFieldPo(InjectionField rf) {
        this.api = rf.api();
        this.feign = rf.feign();
        this.key = rf.key();
        this.method = rf.method();
    }
}
