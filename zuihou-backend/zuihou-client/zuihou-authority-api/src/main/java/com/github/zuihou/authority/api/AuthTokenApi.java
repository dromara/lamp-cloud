package com.github.zuihou.authority.api;

import com.github.zuihou.auth.utils.Token;
import com.github.zuihou.authority.api.hystrix.AuthTokenApiHystrix;
import com.github.zuihou.authority.dto.auth.LoginDTO;
import com.github.zuihou.base.R;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2019/06/25
 */
@FeignClient(name = "${zuihou.feign.authority-server:zuihou-authority-server}", fallback = AuthTokenApiHystrix.class)
public interface AuthTokenApi {

    /**
     * 刷新token
     *
     * @param account  登录账号
     * @param password
     * @return
     */
    @RequestMapping(value = "/anno/token", method = RequestMethod.GET)
    R<Token> token(@RequestParam("account") String account, @RequestParam("password") String password);

    /**
     * 登录接口
     *
     * @param account  登录账号
     * @param password
     * @return
     */
    @RequestMapping(value = "/anno/login", method = RequestMethod.GET)
    R<LoginDTO> login(@RequestParam("account") String account, @RequestParam("password") String password);

}
