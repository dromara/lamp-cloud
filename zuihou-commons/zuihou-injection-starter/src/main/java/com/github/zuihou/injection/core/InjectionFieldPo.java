package com.github.zuihou.injection.core;

import cn.hutool.core.util.StrUtil;
import com.github.zuihou.injection.annonation.InjectionField;
import com.google.common.base.Objects;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author zuihou
 * @date 2020年02月03日18:48:29
 */
@Data
@NoArgsConstructor
@ToString
public class InjectionFieldPo {

    /**
     * 固定的查询值
     *
     * @return
     */
    protected String key;

    /**
     * 执行查询任务的类
     * <p/>
     * api()  和 feign() 任选其一,  使用 api时，请填写实现类， 使用feign时，填写接口即可
     * 如： @InjectionField(api="userServiceImpl") 等价于 @InjectionField(feign=UserService.class)
     * 如： @InjectionField(api="userController") 等价于 @InjectionField(feign=UserApi.class)
     *
     * @return
     */
    protected String api;

    /**
     * 执行查询任务的类
     * <p/>
     * api()  和 feign() 任选其一,  使用 api时，请填写实现类， 使用feign时，填写接口即可
     * 如： @InjectionField(api="userServiceImpl") 等价于 @InjectionField(feign=UserService.class)
     * 如： @InjectionField(api="userController") 等价于 @InjectionField(feign=UserApi.class)
     */
    protected Class<? extends Object> feign;

    /**
     * 调用方法
     *
     * @return
     */
    protected String method;

    public InjectionFieldPo(InjectionField rf) {
        this.api = rf.api();
        this.feign = rf.feign();
        this.key = rf.key();
        this.method = rf.method();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InjectionFieldPo that = (InjectionFieldPo) o;

        boolean isEquals = Objects.equal(method, that.method);

        if (StrUtil.isNotEmpty(api)) {
            isEquals = isEquals && Objects.equal(api, that.api);
        } else {
            isEquals = isEquals && Objects.equal(feign, that.feign);
        }

        return isEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(api, feign, method);
    }
}
