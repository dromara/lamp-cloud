package top.tangyh.lamp.authority.api.hystrix;

import top.tangyh.lamp.authority.api.UserBizApi;
import top.tangyh.lamp.authority.entity.auth.User;
import top.tangyh.basic.base.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户API熔断
 *
 * @author zuihou
 * @date 2019/07/23
 */
@Component
public class UserBizApiFallback implements UserBizApi {
    @Override
    public R<List<Long>> findAllUserId() {
        return R.timeout();
    }

    @Override
    public R<List<User>> findUserById(List<Long> ids) {
        return R.timeout();
    }
}
