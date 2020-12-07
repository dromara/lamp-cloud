package com.tangyh.lamp.oauth.api.hystrix;

import com.tangyh.lamp.oauth.api.RoleApi;
import com.tangyh.basic.base.R;
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
