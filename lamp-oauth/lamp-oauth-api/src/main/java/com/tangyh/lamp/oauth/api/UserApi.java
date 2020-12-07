package com.tangyh.lamp.oauth.api;

import com.tangyh.lamp.oauth.api.hystrix.UserApiFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * 用户
 *
 * @author zuihou
 * @date 2019/07/02
 */
@FeignClient(name = "${lamp.feign.oauth-server:lamp-oauth-server}", fallback = UserApiFallback.class
        , path = "/user", qualifier = "userApi")
public interface UserApi {
    /**
     * 根据用户id查询权限范围
     *
     * @param id 用户id
     * @return 权限范围
     */
    @RequestMapping(value = "/ds/{id}", method = RequestMethod.GET)
    Map<String, Object> getDataScopeById(@PathVariable("id") Long id);

    /**
     * 根据id查询用户
     *
     * @param ids id
     * @return id-user
     */
    @GetMapping("/findUserByIds")
    Map<Serializable, Object> findUserByIds(@RequestParam(value = "ids") Set<Serializable> ids);

    /**
     * 根据id查询用户名称
     *
     * @param ids id
     * @return id-name
     */
    @GetMapping("/findUserNameByIds")
    Map<Serializable, Object> findUserNameByIds(@RequestParam(value = "ids") Set<Serializable> ids);
}
