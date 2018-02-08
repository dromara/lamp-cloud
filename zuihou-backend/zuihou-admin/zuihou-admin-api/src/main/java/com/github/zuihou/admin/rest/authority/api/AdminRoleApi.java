package com.github.zuihou.admin.rest.authority.api;

import com.github.pagehelper.PageInfo;
import com.github.zuihou.admin.rest.authority.api.hystrix.AdminRoleApiHystrix;
import com.github.zuihou.admin.rest.authority.dto.AdminRoleDTO;
import com.github.zuihou.admin.rest.authority.dto.AdminRolePageReqDTO;
import com.github.zuihou.admin.rest.authority.dto.AdminRoleSaveDTO;
import com.github.zuihou.admin.rest.authority.dto.AdminRoleUpdateDTO;
import com.github.zuihou.base.Result;
import com.github.zuihou.page.plugins.openapi.OpenApiReq;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
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
@FeignClient(name = "${zuihou.feign-server.gateway:zuihou-gateway-server}", fallback = AdminRoleApiHystrix.class)
public interface AdminRoleApi {

    /**
     * 调用者实际传入的参数应该是 Token + id
     * appId 由zuul 解析后，自动传入
     *
     * @param appId
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/admin/role/{id}", method = RequestMethod.GET)
    Result<AdminRoleDTO> getRoleByAppIdAndId(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/api/admin/role/code/{code}", method = RequestMethod.GET)
    Result<AdminRoleDTO> getRoleByAppIdAndCode(@PathVariable(value = "code") String code);

    /**
     * 查询所有角色
     *
     * @return
     */
    @RequestMapping(value = "/api/admin/role/page", method = RequestMethod.GET)
    Result<PageInfo<AdminRoleDTO>> page(OpenApiReq openApiReq, AdminRolePageReqDTO rolePageReqDto);

    @RequestMapping(value = "/api/admin/role", method = RequestMethod.POST)
    Result<AdminRoleDTO> save(@RequestBody AdminRoleSaveDTO adminRoleSaveDTO);

    @RequestMapping(value = "/api/admin/role", method = RequestMethod.PUT)
    Result<Boolean> update(@RequestBody AdminRoleUpdateDTO adminRoleUpdateDTO);

    @RequestMapping(value = "/api/admin/role/{id}", method = RequestMethod.DELETE)
    Result<Boolean> remove(@PathVariable(value = "id") Long id);

}
