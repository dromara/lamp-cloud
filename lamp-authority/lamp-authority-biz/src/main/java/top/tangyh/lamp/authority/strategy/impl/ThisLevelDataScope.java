package top.tangyh.lamp.authority.strategy.impl;

import top.tangyh.lamp.authority.dao.auth.UserMapper;
import top.tangyh.lamp.authority.entity.auth.User;
import top.tangyh.lamp.authority.strategy.AbstractDataScopeHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 本级
 *
 * @author zuihou
 * @version 1.0
 * @date 2019-06-08 15:44
 */
@Component("THIS_LEVEL")
@RequiredArgsConstructor
public class ThisLevelDataScope implements AbstractDataScopeHandler {
    private final UserMapper userMapper;

    @Override
    public List<Long> getOrgIds(List<Long> orgList, Long userId) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            return Collections.emptyList();
        }
        Long orgId = user.getOrgId();
        return Arrays.asList(orgId);
    }
}
