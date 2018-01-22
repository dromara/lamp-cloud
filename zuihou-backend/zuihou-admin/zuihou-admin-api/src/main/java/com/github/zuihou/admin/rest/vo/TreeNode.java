package com.github.zuihou.admin.rest.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zuihou
 * @createTime 2017-12-20 11:33
 */
@Data
public class TreeNode<T> {
    @ApiModelProperty(value = "id")
    protected long id;
    @ApiModelProperty(value = "父id")
    protected long parentId;
    @ApiModelProperty(value = "子tree")
    protected List<T> children = new ArrayList<>();

    @JsonIgnore
    public void add(T node) {
        children.add(node);
    }
}
