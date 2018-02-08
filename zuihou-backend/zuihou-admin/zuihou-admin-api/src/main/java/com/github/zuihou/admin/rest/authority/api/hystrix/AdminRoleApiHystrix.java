package com.github.zuihou.admin.rest.authority.api.hystrix;

import com.github.pagehelper.PageInfo;
import com.github.zuihou.admin.rest.authority.api.AdminRoleApi;
import com.github.zuihou.admin.rest.authority.dto.AdminRoleDTO;
import com.github.zuihou.admin.rest.authority.dto.AdminRolePageReqDTO;
import com.github.zuihou.admin.rest.authority.dto.AdminRoleSaveDTO;
import com.github.zuihou.admin.rest.authority.dto.AdminRoleUpdateDTO;
import com.github.zuihou.base.Result;
import com.github.zuihou.page.plugins.openapi.OpenApiReq;
import org.springframework.stereotype.Component;

/**
 * 熔断器默认实现
 *
 * @author zuihou
 * @createTime 2017-12-08 16:11
 */
@Component
public class AdminRoleApiHystrix implements AdminRoleApi {
    @Override
    public Result<AdminRoleDTO> getRoleByAppIdAndId(Long id) {
        return Result.timeout();
    }

    @Override
    public Result<AdminRoleDTO> getRoleByAppIdAndCode(String code) {
        return Result.timeout();
    }

    @Override
    public Result<PageInfo<AdminRoleDTO>> page(OpenApiReq openApiReq, AdminRolePageReqDTO rolePageReqDto) {
        return Result.timeout();
    }

    @Override
    public Result<AdminRoleDTO> save(AdminRoleSaveDTO adminRoleSaveDTO) {
        return Result.timeout();
    }

    @Override
    public Result<Boolean> update(AdminRoleUpdateDTO adminRoleUpdateDTO) {
        return Result.timeout();
    }

    @Override
    public Result<Boolean> remove(Long id) {
        return Result.timeout();
    }
}
