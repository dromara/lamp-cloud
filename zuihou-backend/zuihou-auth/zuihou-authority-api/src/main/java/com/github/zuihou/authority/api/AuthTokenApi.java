package com.github.zuihou.authority.api;

import com.github.zuihou.auth.utils.Token;
import com.github.zuihou.authority.dto.auth.LoginDTO;
import com.github.zuihou.base.Result;

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
@FeignClient(name = "${zuihou.feign.authority-server:zuihou-authority-server}")
public interface AuthTokenApi {

    /**
     * 刷新token
     *
     * @param account  登录账号
     * @param password
     * @return
     */
    @RequestMapping(value = "/anno/token", method = RequestMethod.GET)
    Result<Token> token(@RequestParam("account") String account, @RequestParam("password") String password);

    /**
     * 登录接口
     *
     * @param account  登录账号
     * @param password
     * @return
     */
    @RequestMapping(value = "/anno/login", method = RequestMethod.GET)
    Result<LoginDTO> login(@RequestParam("account") String account, @RequestParam("password") String password);

}
