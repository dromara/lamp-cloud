package com.github.zuihou.authority.service.defaults;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.zuihou.authority.dto.defaults.GlobalUserSaveDTO;
import com.github.zuihou.authority.dto.defaults.GlobalUserUpdateDTO;
import com.github.zuihou.authority.entity.defaults.GlobalUser;

/**
 * <p>
 * 业务接口
 * 全局账号
 * </p>
 *
 * @author zuihou
 * @date 2019-10-25
 */
public interface GlobalUserService extends IService<GlobalUser> {

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
    void removeByIds(String tenantCode, Long[] ids);

    /**
     * 修改
     *
     * @param data
     * @return
     */
    GlobalUser update(GlobalUserUpdateDTO data);
}
