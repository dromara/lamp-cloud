package com.github.zuihou.admin.repository.account.dao;

import com.github.zuihou.admin.entity.account.domain.AccountDO;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper extends com.github.zuihou.base.dao.BaseDao<Long, com.github.zuihou.admin.entity.account.po.Admin, com.github.zuihou.admin.repository.account.example.AdminExample> {
    AccountDO getAccount(@Param("userName") String userName);
}