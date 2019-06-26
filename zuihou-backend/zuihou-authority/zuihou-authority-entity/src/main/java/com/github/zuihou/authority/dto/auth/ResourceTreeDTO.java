package com.github.zuihou.authority.dto.auth;

import java.util.List;

import com.github.zuihou.authority.entity.auth.Resource;
import com.github.zuihou.common.utils.ITreeNode;

import io.swagger.annotations.ApiModel;
import lombok.ToString;

/**
 * This is a Description
 *
 * @author tangyh
 * @date 2019/06/05
 */
@ToString(callSuper = true)
@ApiModel(value = "ResourceTreeDTO", description = "资源树")
public class ResourceTreeDTO extends Resource implements ITreeNode<ResourceTreeDTO, Long> {
    private List<ResourceTreeDTO> children;

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public Long getCreateUser() {
        return super.getCreateUser();
    }

    @Override
    public Long getUpdateUser() {
        return super.getUpdateUser();
    }

    @Override
    public void add(ResourceTreeDTO node) {
        children.add(node);
    }

    @Override
    public List<ResourceTreeDTO> getChildren() {
        return this.children;
    }

    @Override
    public void setChildren(List<ResourceTreeDTO> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }


    @Override
    public Long getParentId() {
        return super.getMenuId();
    }
}
