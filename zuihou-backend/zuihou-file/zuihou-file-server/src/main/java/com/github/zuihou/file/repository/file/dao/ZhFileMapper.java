package com.github.zuihou.file.repository.file.dao;

import org.apache.ibatis.annotations.Param;

public interface ZhFileMapper extends com.github.zuihou.base.dao.BaseDao<Long, com.github.zuihou.file.entity.file.po.ZhFile, com.github.zuihou.file.repository.file.example.ZhFileExample> {

    Integer removeDirByAppIdAndId(@Param("appId") String appId, @Param("id") Long id);
}