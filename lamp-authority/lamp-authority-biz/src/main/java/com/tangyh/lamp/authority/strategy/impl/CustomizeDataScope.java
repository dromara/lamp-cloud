package com.tangyh.lamp.authority.strategy.impl;

import com.tangyh.basic.exception.BizException;
import com.tangyh.basic.exception.code.ExceptionCode;
import com.tangyh.lamp.authority.strategy.AbstractDataScopeHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义模式
 *
 * @author zuihou
 * @version 1.0
 * @date 2019-06-08 16:31
 */
@Component("CUSTOMIZE")
@RequiredArgsConstructor
public class CustomizeDataScope implements AbstractDataScopeHandler {

    @Override
    public List<Long> getOrgIds(List<Long> orgList, Long userId) {
        if (orgList == null || orgList.isEmpty()) {
            throw new BizException(ExceptionCode.BASE_VALID_PARAM.getCode(), "自定义数据权限类型时，组织不能为空");
        }
        return orgList;
    }
}
