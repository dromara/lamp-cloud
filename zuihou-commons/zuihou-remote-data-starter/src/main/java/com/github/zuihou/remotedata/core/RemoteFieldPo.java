package com.github.zuihou.remotedata.core;

import com.github.zuihou.remotedata.annonation.RemoteField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"key"})
public class RemoteFieldPo {

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

    public RemoteFieldPo(RemoteField rf) {
        this.api = rf.api();
        this.key = rf.key();
        this.method = rf.method();
    }
}
