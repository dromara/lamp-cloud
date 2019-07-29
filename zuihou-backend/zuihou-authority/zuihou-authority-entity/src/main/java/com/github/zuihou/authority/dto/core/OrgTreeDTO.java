package com.github.zuihou.authority.dto.core;

import java.util.List;

import com.github.zuihou.authority.entity.core.Org;
import com.github.zuihou.model.ITreeNode;

import io.swagger.annotations.ApiModel;
import lombok.ToString;

/**
 * 组织树
 *
 * @author zuihou
 * @date 2019/07/29
 */
@ToString(callSuper = true)
@ApiModel(value = "OrgTreeDTO", description = "组织树")
public class OrgTreeDTO extends Org implements ITreeNode<OrgTreeDTO, Long> {
    private List<OrgTreeDTO> children;

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
    public void add(OrgTreeDTO node) {
        children.add(node);
    }

    @Override
    public List<OrgTreeDTO> getChildren() {
        return this.children;
    }

    @Override
    public void setChildren(List<OrgTreeDTO> children) {
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
}
