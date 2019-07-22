package com.github.zuihou.authority.strategy.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.github.zuihou.authority.entity.auth.User;
import com.github.zuihou.authority.service.auth.UserService;
import com.github.zuihou.authority.strategy.AbstractDataScopeHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Classname ThisLevelHandler
 * @Description 本级
 * @Author Created by Lihaodong (alias:小东啊) lihaodongmail@163.com
 * @Date 2019-06-08 15:44
 * @Version 1.0
 */
@Component("THIS_LEVEL")
public class ThisLevelDataScope implements AbstractDataScopeHandler {
    @Autowired
    private UserService userService;

    @Override
    public List<Long> getOrgIds(List<Long> orgList, Long userId) {
        User user = userService.getById(userId);
        if (user == null) {
            return Collections.emptyList();
        }
        return Arrays.asList(user.getOrgId());
    }
}
