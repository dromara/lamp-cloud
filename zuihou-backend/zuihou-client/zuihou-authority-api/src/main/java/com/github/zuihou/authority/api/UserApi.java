package com.github.zuihou.authority.api;

import java.util.Map;

import com.github.zuihou.authority.api.hystrix.UserApiFallback;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2019/07/02
 */
@FeignClient(name = "${zuihou.feign.authority-server:zuihou-authority-server}", fallback = UserApiFallback.class)
public interface UserApi {
    /**
     * 刷新token
     *
     * @param id 用户id
     * @return
     */
    @RequestMapping(value = "/user/ds/{id}", method = RequestMethod.GET)
    Map<String, Object> getDataScopeById(@PathVariable("id") Long id);

}
