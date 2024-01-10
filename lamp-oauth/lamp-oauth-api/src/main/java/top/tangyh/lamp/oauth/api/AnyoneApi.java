package top.tangyh.lamp.oauth.api;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.tangyh.basic.base.R;
import top.tangyh.basic.constant.Constants;

/**
 * 参数API
 *
 * @author zuihou
 * @date 2020年04月02日22:53:56
 */
@FeignClient(name = "${" + Constants.PROJECT_PREFIX + ".feign.oauth-server:lamp-oauth-server}", path = "/anyone")
public interface AnyoneApi {
    /**
     * 检查指定接口是否有访问权限
     *
     * @param path   请求路径
     * @param method 请求方法
     * @return 是否有权限
     */
    @GetMapping("/checkUri")
    R<Boolean> checkUri(@RequestParam("path") String path, @RequestParam("method") String method);

}
