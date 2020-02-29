package com.github.zuihou.authority.strategy.impl;

import com.github.zuihou.authority.entity.core.Org;
import com.github.zuihou.authority.service.core.OrgService;
import com.github.zuihou.authority.strategy.AbstractDataScopeHandler;
import com.github.zuihou.exception.BizException;
import com.github.zuihou.exception.code.ExceptionCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Classname CustomizeDataScope
 * @Description 自定义
 * @Author Created by Lihaodong (alias:小东啊) lihaodongmail@163.com
 * @Date 2019-06-08 16:31
 * @Version 1.0
 */
@Component("CUSTOMIZE")
public class CustomizeDataScope implements AbstractDataScopeHandler {

    @Autowired
    private OrgService orgService;

    @Override
    public List<Long> getOrgIds(List<Long> orgList, Long userId) {
        if (orgList == null || orgList.isEmpty()) {
            throw new BizException(ExceptionCode.BASE_VALID_PARAM.getCode(), "自定义数据权限类型时，组织不能为空");
        }
        List<Org> children = orgService.findChildren(orgList);
        return children.stream().mapToLong(Org::getId).boxed().collect(Collectors.toList());
    }
}
