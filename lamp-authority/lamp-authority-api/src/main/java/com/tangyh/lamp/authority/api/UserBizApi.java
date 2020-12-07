package com.tangyh.lamp.authority.api;

import com.tangyh.lamp.authority.api.hystrix.UserBizApiFallback;
import com.tangyh.lamp.authority.entity.auth.User;
import com.tangyh.basic.base.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 用户
 *
 * @author zuihou
 * @date 2019/07/02
 */
@FeignClient(name = "${lamp.feign.authority-server:lamp-authority-server}", fallback = UserBizApiFallback.class
        , path = "/user", qualifier = "userBizApi")
public interface UserBizApi {

    /**
     * 查询所有的用户id
     *
     * @return 全部用户的id
     */
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    R<List<Long>> findAllUserId();

    /**
     * 根据id集合查询所有的用户
     *
     * @param ids id集合
     * @return 指定用户
     */
    @RequestMapping(value = "/findUserById", method = RequestMethod.GET)
    R<List<User>> findUserById(@RequestParam(value = "ids") List<Long> ids);
}
