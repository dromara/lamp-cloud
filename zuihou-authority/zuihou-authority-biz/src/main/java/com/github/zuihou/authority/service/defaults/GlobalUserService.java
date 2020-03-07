package com.github.zuihou.authority.service.defaults;

import com.github.zuihou.authority.dto.defaults.GlobalUserSaveDTO;
import com.github.zuihou.authority.dto.defaults.GlobalUserUpdateDTO;
import com.github.zuihou.authority.entity.defaults.GlobalUser;
import com.github.zuihou.base.service.SuperService;

import java.util.List;

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
     * @param tenantCode
     * @param account
     * @return
     */
    Boolean check(String tenantCode, String account);

    /**
     * 新建用户
     *
     * @param data
     * @return
     */
    GlobalUser save(GlobalUserSaveDTO data);

    /**
     * 删除全局用户
     *
     * @param ids
     */
    boolean removeByIds(String tenantCode, List<Long> ids);

    /**
     * 修改
     *
     * @param data
     * @return
     */
    GlobalUser update(GlobalUserUpdateDTO data);
}
