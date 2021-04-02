package com.tangyh.lamp.oauth.api.hystrix;

import com.tangyh.lamp.oauth.api.UserApi;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 用户API熔断
 *
 * @author zuihou
 * @date 2019/07/23
 */
@Component
public class UserApiFallback implements UserApi {
    @Override
    public Map<String, Object> getDataScopeById(Long id) {
        Map<String, Object> map = new HashMap<>(4);
        map.put("dsType", 5);
        map.put("orgIds", Collections.emptyList());
        return map;
    }

    @Override
    public Map<Serializable, Object> findNameByIds(Set<Serializable> ids) {
        return Collections.emptyMap();
    }

    @Override
    public Map<Serializable, Object> findByIds(Set<Serializable> ids) {
        return Collections.emptyMap();
    }
}
