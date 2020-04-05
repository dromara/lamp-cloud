package com.github.zuihou.oauth.api.hystrix;

import com.github.zuihou.base.R;
import com.github.zuihou.oauth.api.RoleApi;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 角色查询API
 *
 * @author zuihou
 * @date 2019/08/02
 */
@Component
public class RoleApiFallback implements RoleApi {
    @Override
    public R<List<Long>> findUserIdByCode(String[] codes) {
        return R.timeout();
    }
}
