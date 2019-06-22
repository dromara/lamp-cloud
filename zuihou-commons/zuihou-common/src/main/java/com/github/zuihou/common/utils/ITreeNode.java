package com.github.zuihou.common.utils;

import java.io.Serializable;
import java.util.List;

/**
 * 树接口
 *
 * @param <T>
 * @param <I>
 * @author zuihou
 * @date 2019-06-22 22:49
 */
public interface ITreeNode<T, I extends Serializable> {

    /**
     * 添加
     *
     * @param node
     */
    void add(T node);

    /**
     * 获取id
     *
     * @return
     */
    I getId();

    /**
     * 获取父id
     *
     * @return
     */
    I getParentId();

    /**
     * 获取子类
     *
     * @return
     */
    List<T> getChildren();

    /**
     * 设置子类
     *
     * @param children
     */
    void setChildren(List<T> children);

}