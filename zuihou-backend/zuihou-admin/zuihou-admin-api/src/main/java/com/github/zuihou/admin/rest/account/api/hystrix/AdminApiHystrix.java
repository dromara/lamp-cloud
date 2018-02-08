package com.github.zuihou.admin.rest.account.api.hystrix;

import com.github.zuihou.admin.rest.account.api.AdminApi;
import com.github.zuihou.admin.rest.account.dto.AccountDTO;
import com.github.zuihou.admin.rest.account.dto.AdminDTO;
import com.github.zuihou.admin.rest.account.dto.AdminRegisterDTO;
import com.github.zuihou.base.Result;
import org.springframework.stereotype.Component;

/**
 * @author zuihou
 * @createTime 2018-01-02 16:17
 */
@Component
public class AdminApiHystrix implements AdminApi {
    @Override
    public Result<AdminDTO> getByPwd(String userName, String passWord) {
        return Result.timeout();
    }

    @Override
    public Result<AccountDTO> getAccount(String userName) {
        return Result.timeout();
    }

    @Override
    public Result<AdminDTO> get(String userName) {
        return Result.timeout();
    }

    @Override
    public Result<Boolean> check(String userName) {
        return Result.timeout();
    }

    @Override
    public Result<Boolean> registry(AdminRegisterDTO adminRegisterDTO) {
        return Result.timeout();
    }
}
