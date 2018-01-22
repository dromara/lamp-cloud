package com.github.zuihou.admin.rest.authority.api;

import com.github.pagehelper.PageInfo;
import com.github.zuihou.admin.rest.authority.api.hystrix.AdminRoleApiHystrix;
import com.github.zuihou.admin.rest.authority.dto.AdminRoleDto;
import com.github.zuihou.admin.rest.authority.dto.AdminRolePageReqDto;
import com.github.zuihou.admin.rest.authority.dto.AdminRoleSaveDto;
import com.github.zuihou.admin.rest.authority.dto.AdminRoleUpdateDto;
import com.github.zuihou.base.Result;
import com.github.zuihou.page.plugins.openapi.OpenApiReq;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * api 中的 RequestMapping 影响消费者
 * apiImpl 中的RequestMapping 影响生产者
 * 类上面不能加  //@RequestMapping("role")
 *
 * @author zuihou
 * @createTime 2017-12-08 16:07
 */
@FeignClient(name = "${zuihou.admin.feign.server:zuihou-admin-server}", fallback = AdminRoleApiHystrix.class)
public interface AdminRoleApi {

    /**
     * 调用者实际传入的参数应该是 Token + id
     * appId 由zuul 解析后，自动传入
     *
     * @param appId
     * @param id
     * @return
     */
    @RequestMapping(value = "/role/get", method = RequestMethod.GET)
    Result<AdminRoleDto> getRoleByAppIdAndId(@RequestParam("id") Long id);

    @RequestMapping(value = "/role/getByCode", method = RequestMethod.GET)
    Result<AdminRoleDto> getRoleByAppIdAndCode(@RequestParam("code") String code);

    /**
     * 查询所有角色
     *
     * @return
     */
    @RequestMapping(value = "/role/page", method = RequestMethod.GET)
    Result<PageInfo<AdminRoleDto>> page(OpenApiReq openApiReq, AdminRolePageReqDto rolePageReqDto);

    @RequestMapping(value = "/role/save", method = RequestMethod.POST)
    Result<AdminRoleDto> save(@RequestBody AdminRoleSaveDto adminRoleSaveDto);

    @RequestMapping(value = "/role/update", method = RequestMethod.POST)
    Result<Boolean> update(@RequestBody AdminRoleUpdateDto adminRoleUpdateDto);

    @RequestMapping(value = "/role/remove", method = RequestMethod.POST)
    Result<Boolean> remove(@RequestParam("id") Long id);

}
