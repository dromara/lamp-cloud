package com.github.zuihou.authority.api;

import com.github.zuihou.authority.api.hystrix.UserApiFallback;
import com.github.zuihou.base.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户
 *
 * @author zuihou
 * @date 2019/07/02
 */
@FeignClient(name = "${zuihou.feign.authority-server:zuihou-authority-server}", fallback = UserApiFallback.class
        , path = "/user", qualifier = "userApi")
public interface UserApi {
    /**
     * 刷新token
     *
     * @param id 用户id
     * @return
     */
    @RequestMapping(value = "/ds/{id}", method = RequestMethod.GET)
    Map<String, Object> getDataScopeById(@PathVariable("id") Long id);

    /**
     * 查询所有的用户id
     *
     * @return
     */
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    R<List<Long>> findAllUserId();

    /**
     * 根据id查询用户
     *
     * @param codes
     * @return
     */
    @GetMapping("/findUserByIds")
    Map<Serializable, Object> findUserByIds(@RequestParam(value = "ids") Set<Serializable> ids);

    /**
     * 根据id查询用户名称
     *
     * @param codes
     * @return
     */
    @GetMapping("/findUserNameByIds")
    Map<Serializable, Object> findUserNameByIds(@RequestParam(value = "ids") Set<Serializable> ids);
}
