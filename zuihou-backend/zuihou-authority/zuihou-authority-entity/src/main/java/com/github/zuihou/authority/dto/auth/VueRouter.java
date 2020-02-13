package com.github.zuihou.authority.dto.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.zuihou.base.entity.TreeEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 构建 Vue路由
 *
 * @author zuihou
 * @date 2019-10-20 15:17
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VueRouter extends TreeEntity<VueRouter, Long> {
//public class VueRouter implements ITreeNode<VueRouter, Long>, Serializable {

    private static final long serialVersionUID = -3327478146308500708L;

    @Override
    @JsonIgnore
    public Long getId() {
        return this.id;
    }

    @Override
    @JsonIgnore
    public Long getParentId() {
        return this.parentId;
    }

    @ApiModelProperty(value = "路径")
    private String path;
    @ApiModelProperty(value = "菜单名称")
    private String name;
    @ApiModelProperty(value = "组件")
    private String component;
    @ApiModelProperty(value = "重定向")
    private String redirect;

    @ApiModelProperty(value = "元数据")
    private RouterMeta meta;
    @ApiModelProperty(value = "是否隐藏")
    private Boolean hidden = false;
    @ApiModelProperty(value = "总是显示")
    private Boolean alwaysShow = false;

//    @ApiModelProperty(value = "子路由")
//    private List<VueRouter> children;
//
//    @Override
//    public List<VueRouter> getChildren() {
//        return this.children;
//    }

    public Boolean getAlwaysShow() {
        return getChildren() != null && !getChildren().isEmpty();
    }

    public String getComponent() {
        if (getChildren() != null && !getChildren().isEmpty()) {
            return "Layout";
        }
        return this.component;
    }
}
