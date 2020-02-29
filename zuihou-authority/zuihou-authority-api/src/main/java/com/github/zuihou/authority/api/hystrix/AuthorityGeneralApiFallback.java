package com.github.zuihou.authority.api.hystrix;

import com.github.zuihou.authority.api.AuthorityGeneralApi;
import com.github.zuihou.base.R;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 查询通用数据
 *
 * @author zuihou
 * @date 2019/07/26
 */
@Component
public class AuthorityGeneralApiFallback implements AuthorityGeneralApi {
    @Override
    public R<Map<String, Map<String, String>>> enums() {
        return R.timeout();
    }
}
