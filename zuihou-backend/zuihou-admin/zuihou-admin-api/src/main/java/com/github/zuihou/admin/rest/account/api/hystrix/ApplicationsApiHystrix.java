package com.github.zuihou.admin.rest.account.api.hystrix;

import com.github.zuihou.admin.rest.account.api.ApplicationsApi;
import com.github.zuihou.admin.rest.account.dto.ApplicationsDTO;
import com.github.zuihou.base.Result;
import org.springframework.stereotype.Service;

/**
 * @author zuihou
 * @createTime 2017-12-08 16:12
 */
@Service
public class ApplicationsApiHystrix implements ApplicationsApi {

    @Override
    public Result<ApplicationsDTO> getBySecret(String appId, String appSecret) {
        return Result.timeout();
    }
}
