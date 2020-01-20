package com.github.zuihou.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 远程数据对象
 *
 * @param <K> ID或者code 等唯一键
 * @param <D> 根据key 远程查询出的数据
 */
@Data
@NoArgsConstructor
public class RemoteData<K, D> implements Serializable {
    private K key;
    private D data;

    public RemoteData(K key) {
        this.key = key;
    }
}
