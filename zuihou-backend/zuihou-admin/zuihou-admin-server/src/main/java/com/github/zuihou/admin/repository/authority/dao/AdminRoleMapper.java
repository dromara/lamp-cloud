package com.github.zuihou.admin.repository.authority.dao;

import com.github.zuihou.admin.entity.authority.po.AdminRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminRoleMapper extends com.github.zuihou.base.dao.BaseDao<Long, com.github.zuihou.admin.entity.authority.po.AdminRole, com.github.zuihou.admin.repository.authority.example.AdminRoleExample> {
    List<AdminRole> findRole(@Param("appId") String appId, @Param("applicationId") Long applicationId);
}