package com.github.zuihou.utils;


import com.github.zuihou.base.entity.TreeEntity;
import com.github.zuihou.model.ITreeNode;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * list列表 转换成tree列表
 * Created by Ace on 2017/6/12.
 *
 * @author zuihou
 */
public class TreeUtil {

    /**
     * 复杂度O(N^2)<br/>
     * 建立多个根节点树
     * 保证构造后的树中，同一级的节点间的相互顺序与在传入数据中的相互顺序保持一致。
     *
     * @param treeNodes
     * @return
     */
    @Deprecated
    public static <T extends ITreeNode<T, ? extends Serializable>> List<T> build(List<T> treeNodes) {
        if (CollectionUtils.isEmpty(treeNodes)) {
            return treeNodes;
        }
        //记录自己是自己的父节点的id集合
        List<Serializable> selfIdEqSelfParent = new ArrayList<>();
        // 为每一个节点找到子节点集合
        for (T parent : treeNodes) {
            Serializable id = parent.getId();
            for (T children : treeNodes) {
                if (parent != children) {
                    //parent != children 这个来判断自己的孩子不允许是自己，因为有时候，根节点的parent会被设置成为自己
                    if (id.equals(children.getParentId())) {
//                        if (parent.getChildren() == null) {
//                            parent.setChildren(new ArrayList<T>());
//                        }
                        parent.initChildren();
                        parent.getChildren().add(children);
                    }
                } else if (id.equals(parent.getParentId())) {

                    selfIdEqSelfParent.add(id);
                }
            }
        }
        // 找出根节点集合
        List<T> trees = new ArrayList<>();

//        List<Serializable> allIds = new ArrayList<>();
//        for (T baseNode : treeNodes) {
//            allIds.add(baseNode.getId());
//        }

        List<? extends Serializable> allIds = treeNodes.stream().map(node -> node.getId()).collect(Collectors.toList());
        for (T baseNode : treeNodes) {
            if (!allIds.contains(baseNode.getParentId()) || selfIdEqSelfParent.contains(baseNode.getParentId())) {
                trees.add(baseNode);
            }
        }
        return trees;
    }


    /**
     * 构建Tree结构
     *
     * @param treeList
     * @param <E>      实体
     * @param <?>      主键
     * @return
     */
    public static <E extends TreeEntity<E, ? extends Serializable>> List<E> buildTree(List<E> treeList) {
        if (CollectionUtils.isEmpty(treeList)) {
            return treeList;
        }
        //记录自己是自己的父节点的id集合
        List<Serializable> selfIdEqSelfParent = new ArrayList<>();
        // 为每一个节点找到子节点集合
        for (E parent : treeList) {
            Serializable id = parent.getId();
            for (E children : treeList) {
                if (parent != children) {
                    //parent != children 这个来判断自己的孩子不允许是自己，因为有时候，根节点的parent会被设置成为自己
                    if (id.equals(children.getParentId())) {
                        parent.initChildren();
                        parent.getChildren().add(children);
                    }
                } else if (id.equals(parent.getParentId())) {
                    selfIdEqSelfParent.add(id);
                }
            }
        }
        // 找出根节点集合
        List<E> trees = new ArrayList<>();

        List<? extends Serializable> allIds = treeList.stream().map(node -> node.getId()).collect(Collectors.toList());
        for (E baseNode : treeList) {
            if (!allIds.contains(baseNode.getParentId()) || selfIdEqSelfParent.contains(baseNode.getParentId())) {
                trees.add(baseNode);
            }
        }
        return trees;
    }
}
