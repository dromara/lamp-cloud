package top.tangyh.lamp.common.api;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import top.tangyh.basic.base.R;
import top.tangyh.basic.constant.Constants;

import java.util.List;
import java.util.Map;

/**
 * 参数API
 *
 * @author zuihou
 * @date 2020年04月02日22:53:56
 */
@FeignClient(name = "${" + Constants.PROJECT_PREFIX + ".feign.oauth-server:lamp-oauth-server}")
public interface HelperApi {

    /**
     * 根据参数键查询参数值
     *
     * @param keys
     * @return 参数值
     */
    @PostMapping("/anyUser/parameter/findParamMapByKey")
    R<Map<String, String>> findParams(@RequestBody List<String> keys);

}
