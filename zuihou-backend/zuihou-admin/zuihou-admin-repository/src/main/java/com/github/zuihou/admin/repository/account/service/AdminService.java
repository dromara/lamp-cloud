package com.github.zuihou.admin.repository.account.service;

import com.github.zuihou.admin.entity.account.po.Admin;
import com.github.zuihou.admin.repository.account.example.AdminExample;
import com.github.zuihou.base.service.BaseService;

/**
 * @author zuihou
 * @createTime 2017-12-15 11:13
 */
public interface AdminService extends BaseService<Long, Admin, AdminExample> {
    Admin get(String userName, String passWord);

}
