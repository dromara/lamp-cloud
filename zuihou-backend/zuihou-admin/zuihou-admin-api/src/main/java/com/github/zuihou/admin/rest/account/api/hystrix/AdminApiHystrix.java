package com.github.zuihou.admin.rest.account.api.hystrix;

import com.github.zuihou.admin.rest.account.api.AdminApi;
import com.github.zuihou.admin.rest.account.dto.AdminDto;
import com.github.zuihou.admin.rest.account.dto.AdminRegisterDto;
import com.github.zuihou.base.Result;
import org.springframework.stereotype.Component;

/**
 * @author zuihou
 * @createTime 2018-01-02 16:17
 */
@Component
public class AdminApiHystrix implements AdminApi {
    @Override
    public Result<AdminDto> getByPwd(String userName, String passWord) {
        return Result.timeout();
    }

    @Override
    public Result<AdminDto> get(String userName) {
        return Result.timeout();
    }

    @Override
    public Result<Boolean> check(String userName) {
        return Result.timeout();
    }

    @Override
    public Result<Boolean> registry(AdminRegisterDto adminRegisterDto) {
        return Result.timeout();
    }
}
