package com.github.zuihou.tenant.service;

import com.github.zuihou.base.service.SuperCacheService;
import com.github.zuihou.tenant.dto.TenantSaveDTO;
import com.github.zuihou.tenant.entity.Tenant;

import java.util.List;

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
//    Tenant saveInit(TenantSaveInitDTO data);

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

    /**
     * 删除租户数据
     *
     * @param ids
     * @return
     */
    Boolean delete(List<Long> ids);
}
