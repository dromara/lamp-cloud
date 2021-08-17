package top.tangyh.lamp.tenant.service.impl;

import cn.hutool.core.convert.Convert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.tangyh.basic.base.service.SuperCacheServiceImpl;
import top.tangyh.basic.cache.model.CacheKey;
import top.tangyh.basic.cache.model.CacheKeyBuilder;
import top.tangyh.basic.database.mybatis.conditions.Wraps;
import top.tangyh.basic.utils.ArgumentAssert;
import top.tangyh.basic.utils.BeanPlusUtil;
import top.tangyh.lamp.common.cache.tenant.TenantCacheKeyBuilder;
import top.tangyh.lamp.common.cache.tenant.TenantCodeCacheKeyBuilder;
import top.tangyh.lamp.file.service.AppendixService;
import top.tangyh.lamp.tenant.dao.TenantMapper;
import top.tangyh.lamp.tenant.dto.TenantConnectDTO;
import top.tangyh.lamp.tenant.dto.TenantSaveDTO;
import top.tangyh.lamp.tenant.dto.TenantUpdateDTO;
import top.tangyh.lamp.tenant.entity.Tenant;
import top.tangyh.lamp.tenant.enumeration.TenantStatusEnum;
import top.tangyh.lamp.tenant.enumeration.TenantTypeEnum;
import top.tangyh.lamp.tenant.service.TenantService;
import top.tangyh.lamp.tenant.strategy.InitSystemContext;

import java.util.List;
import java.util.function.Function;

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
    private final AppendixService appendixService;

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
        ArgumentAssert.isFalse(check(data.getCode()), "编码重复，请重新输入");

        // 1， 保存租户 (默认库)
        Tenant tenant = BeanPlusUtil.toBean(data, Tenant.class);
        tenant.setStatus(TenantStatusEnum.WAIT_INIT);
        tenant.setType(TenantTypeEnum.CREATE);
        // defaults 库
        save(tenant);

        appendixService.save(tenant.getId(), data.getLogos());

        CacheKey cacheKey = new TenantCodeCacheKeyBuilder().key(tenant.getCode());
        cacheOps.set(cacheKey, tenant.getId());
        return tenant;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Tenant update(TenantUpdateDTO model) {
        Tenant tenant = BeanPlusUtil.toBean(model, Tenant.class);
        super.updateById(tenant);
        appendixService.save(tenant.getId(), model.getLogos());
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
        appendixService.removeByBizId(ids);
        return removeByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteAll(List<Long> ids) {
        List<String> tenantCodeList = listObjs(Wraps.<Tenant>lbQ().select(Tenant::getCode).in(Tenant::getId, ids), Convert::toStr);
        if (tenantCodeList.isEmpty()) {
            return true;
        }
        appendixService.removeByBizId(ids);
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
