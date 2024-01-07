package top.tangyh.lamp.oauth.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.tangyh.basic.base.R;
import top.tangyh.basic.constant.Constants;

/**
 * @author tangyh
 * @version v1.0
 * @date 2022/9/29 11:05 PM
 * @create [2022/9/29 11:05 PM ] [tangyh] [初始创建]
 */
@FeignClient(name = "${" + Constants.PROJECT_PREFIX + ".feign.oauth-server:lamp-oauth-server}", path = "/anyTenant")
public interface CaptchaApi {
    @GetMapping(value = "/anyTenant/check")
    R<Boolean> check(@RequestParam(value = "key") String key,
                     @RequestParam(value = "code") String code,
                     @RequestParam(value = "templateCode") String templateCode);
}
