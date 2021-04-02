package com.tangyh.lamp.authority.strategy.impl;

import com.tangyh.lamp.authority.dao.auth.UserMapper;
import com.tangyh.lamp.authority.entity.auth.User;
import com.tangyh.lamp.authority.entity.core.Org;
import com.tangyh.lamp.authority.service.core.OrgService;
import com.tangyh.lamp.authority.strategy.AbstractDataScopeHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 本级以及子级
 *
 * @author zuihou
 * @version 1.0
 * @date 2019-06-08 16:30
 */
@Component("THIS_LEVEL_CHILDREN")
@RequiredArgsConstructor
public class ThisLevelChildrenDataScope implements AbstractDataScopeHandler {
    private final UserMapper userMapper;
    private final OrgService orgService;

    @Override
    public List<Long> getOrgIds(List<Long> orgList, Long userId) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            return Collections.emptyList();
        }
        Long orgId = user.getOrgId();
        List<Org> children = orgService.findChildren(Arrays.asList(orgId));
        return children.stream().mapToLong(Org::getId).boxed().collect(Collectors.toList());
    }

}
