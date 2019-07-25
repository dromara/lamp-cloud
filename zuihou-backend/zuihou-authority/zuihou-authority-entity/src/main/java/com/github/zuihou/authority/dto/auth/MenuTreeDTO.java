package com.github.zuihou.authority.dto.auth;

import java.util.List;

import com.github.zuihou.authority.entity.auth.Menu;
import com.github.zuihou.common.utils.ITreeNode;

import io.swagger.annotations.ApiModel;
import lombok.ToString;

/**
 * 树形菜单 DTO
 *
 * @author zuihou
 * @date 2019/06/05
 */
@ToString(callSuper = true)
@ApiModel(value = "ResourceTreeDTO", description = "资源树")
public class MenuTreeDTO extends Menu implements ITreeNode<MenuTreeDTO, Long> {
    private List<MenuTreeDTO> children;

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
    public void add(MenuTreeDTO node) {
        children.add(node);
    }

    @Override
    public List<MenuTreeDTO> getChildren() {
        return this.children;
    }

    @Override
    public void setChildren(List<MenuTreeDTO> children) {
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
