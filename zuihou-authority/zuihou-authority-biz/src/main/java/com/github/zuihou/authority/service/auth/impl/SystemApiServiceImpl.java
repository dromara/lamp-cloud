package com.github.zuihou.authority.service.auth.impl;

import cn.hutool.core.collection.CollUtil;
import com.github.zuihou.authority.dao.auth.SystemApiMapper;
import com.github.zuihou.authority.dto.auth.SystemApiSaveDTO;
import com.github.zuihou.authority.dto.auth.SystemApiScanSaveDTO;
import com.github.zuihou.authority.entity.auth.SystemApi;
import com.github.zuihou.authority.service.auth.SystemApiService;
import com.github.zuihou.base.service.SuperCacheServiceImpl;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.utils.BeanPlusUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.github.zuihou.common.constant.CacheKey.SYSTEM_API;

/**
 * <p>
 * 业务实现类
 * API接口
 * </p>
 *
 * @author zuihou
 * @date 2019-12-15
 */
@Slf4j
@Service
@CacheConfig(cacheNames = SYSTEM_API)
public class SystemApiServiceImpl extends SuperCacheServiceImpl<SystemApiMapper, SystemApi> implements SystemApiService {
    @Override
    protected String getRegion() {
        return SYSTEM_API;
    }

    @Override
    public Boolean batchSave(SystemApiScanSaveDTO data) {
        List<SystemApiSaveDTO> list = data.getSystemApiList();
        if (CollUtil.isEmpty(list)) {
            return false;
        }

        list.forEach((dto) -> {
            try {
                SystemApi systemApi = BeanPlusUtil.toBean(dto, SystemApi.class);
                SystemApi save = this.getApi(dto.getCode());
                if (save == null) {
                    systemApi.setIsOpen(false);
                    systemApi.setIsPersist(true);
                    super.save(systemApi);
                } else {
                    systemApi.setId(save.getId());
                    super.updateById(systemApi);
                }
            } catch (Exception e) {
                log.warn("api初始化失败", e);
            }
        });

        return true;
    }

    public SystemApi getApi(String code) {
        LbqWrapper<SystemApi> wrapper = Wraps.<SystemApi>lbQ().eq(SystemApi::getCode, code);
        return baseMapper.selectOne(wrapper);
    }
}
