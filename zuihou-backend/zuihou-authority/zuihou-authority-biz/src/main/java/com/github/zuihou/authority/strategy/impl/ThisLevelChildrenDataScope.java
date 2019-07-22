package com.github.zuihou.authority.strategy.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.github.zuihou.authority.entity.auth.User;
import com.github.zuihou.authority.entity.core.Org;
import com.github.zuihou.authority.service.auth.UserService;
import com.github.zuihou.authority.service.core.OrgService;
import com.github.zuihou.authority.strategy.AbstractDataScopeHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Classname ThisLevelChildenDataScope
 * @Description 本级以及子级
 * @Author Created by Lihaodong (alias:小东啊) lihaodongmail@163.com
 * @Date 2019-06-08 16:30
 * @Version 1.0
 */
@Component("THIS_LEVEL_CHILDREN")
public class ThisLevelChildrenDataScope implements AbstractDataScopeHandler {
    @Autowired
    private UserService userService;
    @Autowired
    private OrgService orgService;

    @Override
    public List<Long> getOrgIds(List<Long> orgList, Long userId) {
        User user = userService.getById(userId);
        if (user == null) {
            return Collections.emptyList();
        }
        List<Org> children = orgService.findChildren(Arrays.asList(user.getOrgId()));
        return children.stream().mapToLong(Org::getId).boxed().collect(Collectors.toList());
    }
}
