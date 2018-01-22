package com.github.zuihou.admin.rest.account.api;

import com.github.zuihou.admin.rest.account.dto.AdminDto;
import com.github.zuihou.admin.rest.account.dto.AdminRegisterDto;
import com.github.zuihou.admin.rest.account.api.hystrix.AdminApiHystrix;
import com.github.zuihou.base.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zuihou
 * @createTime 2017-12-15 11:20
 */
@FeignClient(name = "${zuihou.admin.feign.server:zuihou-admin-server}", fallback = AdminApiHystrix.class)
public interface AdminApi {
    /**
     * 根据用户和密码查找用户
     * @param userName 登录名
     * @param passWord 明文密码
     * @return
     */
    @RequestMapping(value = "/admin/getByPwd", method = RequestMethod.GET)
    Result<AdminDto> getByPwd(@RequestParam("userName") String userName, @RequestParam("passWord") String passWord);
    /**
     * 根据用户查找用户
     * @param userName 登录名
     * @return
     */
    @RequestMapping(value = "/admin/get", method = RequestMethod.GET)
    Result<AdminDto> get(@RequestParam("userName") String userName);

    /**
     * 检测登录名是否可用
     * @param userName 登录名
     * @return
     */
    @RequestMapping(value = "/admin/check", method = RequestMethod.GET)
    Result<Boolean> check(@RequestParam("userName") String userName);

    /**
     * 注册帐号
     * @param adminRegisterDto 帐号注册
     * @return
     */
    @RequestMapping(value = "/admin/registry", method = RequestMethod.POST)
    Result<Boolean> registry(@RequestBody AdminRegisterDto adminRegisterDto);
}
