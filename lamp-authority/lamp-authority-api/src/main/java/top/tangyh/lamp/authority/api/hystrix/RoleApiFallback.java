package top.tangyh.lamp.authority.api.hystrix;

import org.springframework.stereotype.Component;
import top.tangyh.basic.base.R;
import top.tangyh.lamp.authority.api.RoleApi;

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
