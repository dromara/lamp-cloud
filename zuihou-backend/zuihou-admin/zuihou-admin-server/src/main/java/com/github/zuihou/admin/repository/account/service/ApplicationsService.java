package com.github.zuihou.admin.repository.account.service;

import com.github.zuihou.admin.entity.account.po.Applications;
import com.github.zuihou.admin.repository.account.example.ApplicationsExample;
import com.github.zuihou.base.service.BaseService;

/**
 * @author zuihou
 * @createTime 2017-12-15 11:08
 */
public interface ApplicationsService extends BaseService<Long, Applications, ApplicationsExample> {

    Applications getBySecret(String appId, String appSecret);

    Applications saveApp(Applications app);
}
