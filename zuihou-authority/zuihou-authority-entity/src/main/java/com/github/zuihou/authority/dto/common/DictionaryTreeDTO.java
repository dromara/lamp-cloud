//package com.github.zuihou.authority.dto.common;
//
//import java.util.List;
//
//import com.github.zuihou.authority.entity.common.Dictionary;
//import com.github.zuihou.model.ITreeNode;
//
//import io.swagger.annotations.ApiModel;
//import lombok.ToString;
//
///**
// * 数据字典树
// *
// * @author zuihou
// * @date 2019/07/30
// */
//@ToString(callSuper = true)
//@ApiModel(value = "DictionaryTreeDTO", description = "数据字典树")
//public class DictionaryTreeDTO extends Dictionary implements ITreeNode<DictionaryTreeDTO, Long> {
//
//    private List<DictionaryTreeDTO> children;
//
//    @Override
//    public Long getId() {
//        return super.getId();
//    }
//
//    @Override
//    public Long getCreateUser() {
//        return super.getCreateUser();
//    }
//
//    @Override
//    public Long getUpdateUser() {
//        return super.getUpdateUser();
//    }
//
//    @Override
//    public void add(DictionaryTreeDTO node) {
//        children.add(node);
//    }
//
//    @Override
//    public List<DictionaryTreeDTO> getChildren() {
//        return this.children;
//    }
//
//    @Override
//    public void setChildren(List<DictionaryTreeDTO> children) {
//        this.children = children;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        return super.equals(o);
//    }
//
//    @Override
//    public int hashCode() {
//        return super.hashCode();
//    }
//}
