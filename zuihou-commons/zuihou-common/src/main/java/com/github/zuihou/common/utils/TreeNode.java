package com.github.zuihou.common.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zuihou
 * @createTime 2017-12-20 11:33
 */
public class TreeNode<T, I extends Serializable> implements ITreeNode<T, I>, Serializable {
    @ApiModelProperty(value = "id")
    protected I id;
    @ApiModelProperty(value = "父id")
    protected I parentId;
    @ApiModelProperty(value = "子tree")
    protected List<T> children = new ArrayList<>();

    @Override
    @JsonIgnore
    public void add(T node) {
        children.add(node);
    }

    @Override
    public I getId() {
        return id;
    }

    public void setId(I id) {
        this.id = id;
    }

    @Override
    public I getParentId() {
        return parentId;
    }

    public void setParentId(I parentId) {
        this.parentId = parentId;
    }

    @Override
    public List<T> getChildren() {
        return children;
    }

    @Override
    public void setChildren(List<T> children) {
        this.children = children;
    }
}
