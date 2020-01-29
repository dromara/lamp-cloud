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
     * 目标类
     *
     * @return
     */
    private String api;

    /**
     * 调用方法
     *
     * @return
     */
    private String method;

    public InjectionFieldPo(InjectionField rf) {
        this.api = rf.api();
        this.key = rf.key();
        this.method = rf.method();
    }
}
