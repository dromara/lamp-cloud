package com.github.zuihou.admin.rest.authority.api.hystrix;

import com.github.pagehelper.PageInfo;
import com.github.zuihou.admin.rest.authority.api.AdminRoleApi;
import com.github.zuihou.admin.rest.authority.dto.AdminRoleDto;
import com.github.zuihou.admin.rest.authority.dto.AdminRolePageReqDto;
import com.github.zuihou.admin.rest.authority.dto.AdminRoleSaveDto;
import com.github.zuihou.admin.rest.authority.dto.AdminRoleUpdateDto;
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
    public Result<AdminRoleDto> getRoleByAppIdAndId(Long id) {
        return Result.timeout();
    }

    @Override
    public Result<AdminRoleDto> getRoleByAppIdAndCode(String code) {
        return Result.timeout();
    }

    @Override
    public Result<PageInfo<AdminRoleDto>> page(OpenApiReq openApiReq, AdminRolePageReqDto rolePageReqDto) {
        return Result.timeout();
    }

    @Override
    public Result<AdminRoleDto> save(AdminRoleSaveDto adminRoleSaveDto) {
        return Result.timeout();
    }

    @Override
    public Result<Boolean> update(AdminRoleUpdateDto adminRoleUpdateDto) {
        return Result.timeout();
    }

    @Override
    public Result<Boolean> remove(Long id) {
        return Result.timeout();
    }
}
