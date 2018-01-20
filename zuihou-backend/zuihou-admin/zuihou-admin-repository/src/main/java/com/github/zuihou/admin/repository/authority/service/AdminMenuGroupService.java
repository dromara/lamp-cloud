package com.github.zuihou.admin.repository.authority.service;

import com.github.zuihou.admin.entity.authority.po.AdminMenuGroup;
import com.github.zuihou.admin.repository.authority.example.AdminMenuGroupExample;
import com.github.zuihou.base.service.BaseService;

/**
 * @author zuihou
 * @createTime 2018-01-02 22:28
 */
public interface AdminMenuGroupService extends BaseService<Long, AdminMenuGroup, AdminMenuGroupExample> {
    boolean check(String appId, String code);
}
