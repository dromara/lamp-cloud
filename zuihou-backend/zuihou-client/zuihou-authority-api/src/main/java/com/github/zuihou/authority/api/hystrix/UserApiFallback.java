package com.github.zuihou.authority.api.hystrix;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.github.zuihou.authority.api.UserApi;

import org.springframework.stereotype.Component;

/**
 * This is a Description
 *
 * @author tangyh
 * @date 2019/07/23
 */
@Component
public class UserApiFallback implements UserApi {
    @Override
    public Map<String, Object> getDataScopeById(Long id) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("dsType", 5);
        map.put("orgIds", Collections.emptyList());
        return map;
    }
}
