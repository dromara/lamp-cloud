package com.github.zuihou.tenant.service;

import com.github.zuihou.base.service.SuperService;
import com.github.zuihou.tenant.dto.GlobalUserSaveDTO;
import com.github.zuihou.tenant.dto.GlobalUserUpdateDTO;
import com.github.zuihou.tenant.entity.GlobalUser;

/**
 * <p>
 * 业务接口
 * 全局账号
 * </p>
 *
 * @author zuihou
 * @date 2019-10-25
 */
public interface GlobalUserService extends SuperService<GlobalUser> {

    /**
     * 检测账号是否可用
     *
     * @param account
     * @return
     */
    Boolean check(String account);

    /**
     * 新建用户
     *
     * @param data
     * @return
     */
    GlobalUser save(GlobalUserSaveDTO data);


    /**
     * 修改
     *
     * @param data
     * @return
     */
    GlobalUser update(GlobalUserUpdateDTO data);
}
