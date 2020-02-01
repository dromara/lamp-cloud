package com.github.zuihou.authority.api.hystrix;

import com.github.zuihou.authority.api.StationApi;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class StationApiFallback implements StationApi {
    @Override
    public Map<Serializable, Object> findStationByIds(Set<Serializable> ids) {
        return new HashMap<>(1);
    }
}
