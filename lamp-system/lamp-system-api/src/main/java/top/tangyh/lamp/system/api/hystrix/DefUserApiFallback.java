package top.tangyh.lamp.system.api.hystrix;

import org.springframework.stereotype.Component;
import top.tangyh.basic.base.R;
import top.tangyh.lamp.system.api.DefUserApi;

import java.util.List;

/**
 * 用户API熔断
 *
 * @author zuihou
 * @date 2019/07/23
 */
@Component
public class DefUserApiFallback implements DefUserApi {
    @Override
    public R<List<Long>> findAllUserId() {
        return R.timeout();
    }
}
