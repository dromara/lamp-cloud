package top.tangyh.lamp.gateway.feign;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import top.tangyh.basic.base.R;
import top.tangyh.basic.context.ContextUtil;
import top.tangyh.lamp.oauth.api.AnyoneApi;

import jakarta.annotation.Resource;
import java.util.concurrent.Future;

/**
 * @author zuihou
 * @date 2021/12/9 18:18
 */
@Component
public class AsyncAnyoneApi {
    @Resource
    @Lazy
    private AnyoneApi anyoneApi;

    @Async
    public Future<R<Boolean>> checkApplication(Long applicationId, Long employeeId) {
        R<Boolean> hasApp = anyoneApi.checkApplication(applicationId, employeeId);
        return new AsyncResult<>(hasApp);
    }

    @Async
    public Future<R<Boolean>> checkUri(Long employeeId, Long applicationId, String path, String method) {
        ContextUtil.setEmployeeId(employeeId);
        ContextUtil.setApplicationId(applicationId);
        R<Boolean> hasUri = anyoneApi.checkUri(path, method);
        return new AsyncResult<>(hasUri);
    }
}
