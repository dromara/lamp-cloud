package com.github.zuihou.injection.core;

import cn.hutool.core.util.StrUtil;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.injection.annonation.InjectionField;
import com.google.common.base.Objects;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zuihou
 * @date 2020年02月03日18:48:15
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class InjectionFieldExtPo extends InjectionFieldPo {

    /**
     * 动态查询值
     */
    private Set<Serializable> keys = new HashSet<>();

    private String tenant;
    private String database;

    public InjectionFieldExtPo(InjectionField rf) {
        super(rf);
    }

    public InjectionFieldExtPo(InjectionFieldPo po, Set<Serializable> keys) {
        this.api = po.getApi();
        this.feign = po.getFeign();
        this.key = po.getKey();
        this.method = po.getMethod();
        this.keys = keys;
        this.tenant = BaseContextHandler.getTenant();
        this.database = BaseContextHandler.getDatabase();
    }

    public InjectionFieldExtPo(InjectionField rf, Set<Serializable> keys) {
        super(rf);
        this.keys = keys;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InjectionFieldExtPo that = (InjectionFieldExtPo) o;

        boolean isEquals = Objects.equal(method, that.method);

        if (StrUtil.isNotEmpty(api)) {
            isEquals = isEquals && Objects.equal(api, that.api);
        } else {
            isEquals = isEquals && Objects.equal(feign, that.feign);
        }

        boolean isEqualsKeys = keys.size() == that.keys.size() && keys.containsAll(that.keys);

        return isEquals && isEqualsKeys;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(api, feign, method, keys);
    }
}
