//package com.github.zuihou.model;
//
//import com.github.zuihou.base.validation.IValidatable;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//
//import java.io.Serializable;
//
///**
// * 远程数据对象
// *
// * @param <K> ID或者code 等唯一键
// * @param <D> 根据key 远程查询出的数据
// * @author zuihou
// * @date 2020年02月02日21:16:22
// */
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//public class RemoteData<K, D> implements Serializable, IValidatable {
//    private K key;
//    private D data;
//
//    public RemoteData(K key) {
//        this.key = key;
//    }
//
//    /**
//     * 获取对象的 主键key
//     *
//     * @param remoteData
//     * @param <K>
//     * @param <D>
//     * @return
//     */
//    public static <K, D> K getKey(RemoteData<K, D> remoteData) {
//        return remoteData != null ? remoteData.getKey() : null;
//    }
//
//    /**
//     * 获取对象的 data
//     *
//     * @param remoteData
//     * @param <K>
//     * @param <D>
//     * @return
//     */
//    public static <K, D> D getData(RemoteData<K, D> remoteData) {
//        return remoteData != null ? remoteData.getData() : null;
//    }
//
//    @Override
//    public Object value() {
//        return this.key;
//    }
//}
