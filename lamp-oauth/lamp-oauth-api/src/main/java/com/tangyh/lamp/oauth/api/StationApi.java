package com.tangyh.lamp.oauth.api;

import com.tangyh.basic.model.LoadService;
import com.tangyh.lamp.oauth.api.hystrix.StationApiFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * 岗位API
 *
 * @author zuihou
 * @date 2019/08/02
 */
@FeignClient(name = "${lamp.feign.oauth-server:lamp-oauth-server}", path = "/station",
        qualifier = "stationApi", fallback = StationApiFallback.class)
public interface StationApi extends LoadService {

    /**
     * 根据id查询 显示名
     *
     * @param ids 唯一键（可能不是主键ID)
     * @return
     */
    @Override
    @GetMapping("/findNameByIds")
    Map<Serializable, Object> findNameByIds(@RequestParam(value = "ids") Set<Serializable> ids);

    /**
     * 根据id查询实体
     *
     * @param ids 唯一键（可能不是主键ID)
     * @return
     */
    @Override
    @GetMapping("/findByIds")
    Map<Serializable, Object> findByIds(@RequestParam(value = "ids") Set<Serializable> ids);
}
