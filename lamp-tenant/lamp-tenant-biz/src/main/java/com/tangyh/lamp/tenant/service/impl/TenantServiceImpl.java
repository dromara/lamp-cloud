package com.tangyh.lamp.tenant.service.impl;

import cn.hutool.core.convert.Convert;
import com.tangyh.basic.base.service.SuperCacheServiceImpl;
import com.tangyh.basic.cache.model.CacheKey;
import com.tangyh.basic.cache.model.CacheKeyBuilder;
import com.tangyh.basic.database.mybatis.conditions.Wraps;
import com.tangyh.basic.utils.BeanPlusUtil;
import com.tangyh.lamp.common.cache.tenant.TenantCacheKeyBuilder;
import com.tangyh.lamp.common.cache.tenant.TenantCodeCacheKeyBuilder;
import com.tangyh.lamp.tenant.dao.TenantMapper;
import com.tangyh.lamp.tenant.dto.TenantConnectDTO;
import com.tangyh.lamp.tenant.dto.TenantSaveDTO;
import com.tangyh.lamp.tenant.entity.Tenant;
import com.tangyh.lamp.tenant.enumeration.TenantStatusEnum;
import com.tangyh.lamp.tenant.enumeration.TenantTypeEnum;
import com.tangyh.lamp.tenant.service.TenantService;
import com.tangyh.lamp.tenant.strategy.InitSystemContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;

import static com.tangyh.basic.utils.BizAssert.isFalse;

/**
 * <p>
 * 业务实现类
 * 企业
 * </p>
 *
 * @author zuihou
 * @date 2019-10-24
 */
@Slf4j
@Service

@RequiredArgsConstructor
public class TenantServiceImpl extends SuperCacheServiceImpl<TenantMapper, Tenant> implements TenantService {

    private final InitSystemContext initSystemContext;

    @Override
    protected CacheKeyBuilder cacheKeyBuilder() {
        return new TenantCacheKeyBuilder();
    }


    /**
     * tenant_name:{tenantCode} -> id 只存租户的id，然后根据id再次查询缓存，这样子的好处是，删除或者修改租户信息时，只需要根据id淘汰缓存即可
     * 缺点就是 每次查询，需要多查一次缓存
     *
     * @param tenant
     * @return
     */
    @Override
    public Tenant getByCode(String tenant) {
        Function<CacheKey, Object> loader = (k) ->
                getObj(Wraps.<Tenant>lbQ().select(Tenant::getId).eq(Tenant::getCode, tenant), Convert::toLong);
        CacheKey cacheKey = new TenantCodeCacheKeyBuilder().key(tenant);
        return getByKey(cacheKey, loader);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Tenant save(TenantSaveDTO data) {
        // defaults 库
        isFalse(check(data.getCode()), "编码重复，请重新输入");

        // 1， 保存租户 (默认库)
        Tenant tenant = BeanPlusUtil.toBean(data, Tenant.class);
        tenant.setStatus(TenantStatusEnum.WAIT_INIT);
        tenant.setType(TenantTypeEnum.CREATE);
        // defaults 库
        save(tenant);

        CacheKey cacheKey = new TenantCodeCacheKeyBuilder().key(tenant.getCode());
        cacheOps.set(cacheKey, tenant.getId());
        return tenant;
    }

    @Override
    public boolean check(String tenantCode) {
        return super.count(Wraps.<Tenant>lbQ().eq(Tenant::getCode, tenantCode)) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean connect(TenantConnectDTO tenantConnect) {
        return initSystemContext.initConnect(tenantConnect) && updateTenantStatus(tenantConnect);
    }

    private Boolean updateTenantStatus(TenantConnectDTO tenantConnect) {
        Boolean flag = this.update(Wraps.<Tenant>lbU()
                .set(Tenant::getStatus, TenantStatusEnum.NORMAL)
                .set(Tenant::getConnectType, tenantConnect.getConnectType())
                .eq(Tenant::getId, tenantConnect.getId()));
        delCache(tenantConnect.getId());
        return flag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(List<Long> ids) {
        List<String> tenantCodeList = listObjs(Wraps.<Tenant>lbQ().select(Tenant::getCode).in(Tenant::getId, ids), Convert::toStr);
        if (tenantCodeList.isEmpty()) {
            return true;
        }
        return removeByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteAll(List<Long> ids) {
        List<String> tenantCodeList = listObjs(Wraps.<Tenant>lbQ().select(Tenant::getCode).in(Tenant::getId, ids), Convert::toStr);
        if (tenantCodeList.isEmpty()) {
            return true;
        }
        removeByIds(ids);
        return initSystemContext.delete(ids, tenantCodeList);
    }

    @Override
    public List<Tenant> find() {
        return list(Wraps.<Tenant>lbQ().eq(Tenant::getStatus, TenantStatusEnum.NORMAL));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateStatus(List<Long> ids, TenantStatusEnum status) {
        boolean update = super.update(Wraps.<Tenant>lbU().set(Tenant::getStatus, status)
                .in(Tenant::getId, ids));

        delCache(ids);
        return update;
    }
}
