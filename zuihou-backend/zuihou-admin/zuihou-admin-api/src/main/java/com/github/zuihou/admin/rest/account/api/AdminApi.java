package com.github.zuihou.admin.rest.account.api;

import com.github.zuihou.admin.rest.account.api.hystrix.AdminApiHystrix;
import com.github.zuihou.admin.rest.account.dto.AccountDTO;
import com.github.zuihou.admin.rest.account.dto.AdminDTO;
import com.github.zuihou.admin.rest.account.dto.AdminRegisterDTO;
import com.github.zuihou.base.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zuihou
 * @createTime 2017-12-15 11:20
 */
@FeignClient(name = "${zuihou.feign-server.gateway:zuihou-gateway-server}", fallback = AdminApiHystrix.class)
public interface AdminApi {
    /**
     * 根据用户和密码查找用户
     * @param userName 登录名
     * @param passWord 明文密码
     * @return
     */
    @RequestMapping(value = "/api/admin/admin/pwd", method = RequestMethod.GET)
    Result<AdminDTO> getByPwd(@RequestParam(value = "userName") String userName, @RequestParam(value = "passWord") String passWord);
    /**
     * 根据用户查找用户
     * @param userName 登录名
     * @return
     */
    @RequestMapping(value = "/api/admin/admin", method = RequestMethod.GET)
    Result<AdminDTO> get(@RequestParam(value = "userName") String userName);

    /**
     * 登录后，获取用户的详细信息
     * @param userName
     * @return
     */
    @RequestMapping(value = "/api/admin/admin/account/{userName}", method = RequestMethod.GET)
    Result<AccountDTO> getAccount(@PathVariable(value = "userName") String userName);


    /**
     * 检测登录名是否可用
     * @param userName 登录名
     * @return
     */
    @RequestMapping(value = "/api/admin/admin/check", method = RequestMethod.GET)
    Result<Boolean> check(@RequestParam(value = "userName") String userName);

    /**
     * 注册帐号
     * @param adminRegisterDTO 帐号注册
     * @return
     */
    @RequestMapping(value = "/api/admin/admin/registry", method = RequestMethod.POST)
    Result<Boolean> registry(@RequestBody AdminRegisterDTO adminRegisterDTO);
}
