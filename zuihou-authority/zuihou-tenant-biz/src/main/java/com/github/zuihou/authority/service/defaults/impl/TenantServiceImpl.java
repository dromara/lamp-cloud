package com.github.zuihou.authority.service.defaults.impl;

import cn.hutool.core.convert.Convert;
import com.github.zuihou.authority.dao.defaults.TenantMapper;
import com.github.zuihou.authority.dto.defaults.TenantSaveDTO;
import com.github.zuihou.authority.entity.defaults.Tenant;
import com.github.zuihou.authority.enumeration.defaults.TenantStatusEnum;
import com.github.zuihou.authority.enumeration.defaults.TenantTypeEnum;
import com.github.zuihou.authority.service.defaults.InitSystemContext;
import com.github.zuihou.authority.service.defaults.TenantService;
import com.github.zuihou.base.service.SuperCacheServiceImpl;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.utils.BeanPlusUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.github.zuihou.common.constant.CacheKey.TENANT;
import static com.github.zuihou.utils.BizAssert.isFalse;

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
public class TenantServiceImpl extends SuperCacheServiceImpl<TenantMapper, Tenant> implements TenantService {

    @Autowired
    private InitSystemContext initSystemContext;

    @Override
    protected String getRegion() {
        return TENANT;
    }

    @Override
    public Tenant getByCode(String tenant) {
        return super.getOne(Wraps.<Tenant>lbQ().eq(Tenant::getCode, tenant));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Tenant save(TenantSaveDTO data) {
        // defaults 库
        isFalse(check(data.getCode()), "编码重复，请重新输入");

        // 1， 保存租户 (默认库)
        Tenant tenant = BeanPlusUtil.toBean(data, Tenant.class);
        tenant.setStatus(TenantStatusEnum.NORMAL);
        tenant.setType(TenantTypeEnum.CREATE);
        // defaults 库
        save(tenant);

        // 3, 初始化库，表, 数据  考虑异步完成 // 租户库
        initSystemContext.init(tenant.getCode());
        return tenant;
    }

    @Override
    public boolean check(String tenantCode) {
        return super.count(Wraps.<Tenant>lbQ().eq(Tenant::getCode, tenantCode)) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(List<Long> ids) {
        List<String> tenantCodeList = listObjs(Wraps.<Tenant>lbQ().select(Tenant::getCode).in(Tenant::getId, ids), Convert::toStr);
        if (tenantCodeList.isEmpty()) {
            return true;
        }
        removeByIds(ids);

        return initSystemContext.delete(tenantCodeList);
    }
}
