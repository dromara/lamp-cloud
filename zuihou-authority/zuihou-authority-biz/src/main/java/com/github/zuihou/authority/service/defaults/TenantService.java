package com.github.zuihou.authority.service.defaults;

import com.github.zuihou.authority.dto.defaults.TenantSaveDTO;
import com.github.zuihou.authority.dto.defaults.TenantSaveInitDTO;
import com.github.zuihou.authority.entity.defaults.Tenant;
import com.github.zuihou.base.service.SuperCacheService;

/**
 * <p>
 * 业务接口
 * 企业
 * </p>
 *
 * @author zuihou
 * @date 2019-10-24
 */
public interface TenantService extends SuperCacheService<Tenant> {
    /**
     * 检测 租户编码是否存在
     *
     * @param tenantCode
     * @return
     */
    boolean check(String tenantCode);

    /**
     * 初始化
     *
     * @param data
     * @return
     */
    Tenant saveInit(TenantSaveInitDTO data);

    /**
     * 保存
     *
     * @param data
     * @return
     */
    Tenant save(TenantSaveDTO data);

    /**
     * 根据编码获取
     *
     * @param tenant
     * @return
     */
    Tenant getByCode(String tenant);
}
