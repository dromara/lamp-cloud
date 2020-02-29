package com.github.zuihou.authority.strategy.impl;

import com.github.zuihou.authority.entity.core.Org;
import com.github.zuihou.authority.service.core.OrgService;
import com.github.zuihou.authority.strategy.AbstractDataScopeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Classname AllDataScope
 * @Description 所有
 * @Author Created by Lihaodong (alias:小东啊) lihaodongmail@163.com
 * @Date 2019-06-08 16:27
 * @Version 1.0
 */
@Component("ALL")
public class AllDataScope implements AbstractDataScopeHandler {

    @Autowired
    private OrgService orgService;

    @Override
    public List<Long> getOrgIds(List<Long> orgList, Long userId) {
        List<Org> list = orgService.lambdaQuery().select(Org::getId).list();
        return list.stream().map(Org::getId).collect(Collectors.toList());
    }


}
