package com.github.zuihou.authority.strategy.impl;

import java.util.List;

import com.github.zuihou.authority.strategy.AbstractDataScopeHandler;

import org.springframework.stereotype.Component;

/**
 * This is a Description
 *
 * @author tangyh
 * @date 2019/07/22
 */
@Component("SELF")
public class SelfDataScope implements AbstractDataScopeHandler {
    @Override
    public List<Long> getOrgIds(List<Long> orgList, Long userId) {
        return null;
    }
}
