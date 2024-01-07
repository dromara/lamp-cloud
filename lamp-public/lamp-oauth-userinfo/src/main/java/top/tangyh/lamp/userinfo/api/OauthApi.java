package top.tangyh.lamp.userinfo.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.tangyh.basic.base.R;
import top.tangyh.basic.constant.Constants;

import java.util.List;

/**
 * @author tangyh
 * @version v1.0
 * @date 2022/9/29 11:05 PM
 * @create [2022/9/29 11:05 PM ] [tangyh] [初始创建]
 */
@FeignClient(name = "${" + Constants.PROJECT_PREFIX + ".feign.oauth-server:lamp-oauth-server}")
public interface OauthApi {

    /**
     * 查询可用的 资源编码
     *
     * @param employeeId    员工ID， 不能为空
     * @param applicationId 应用ID， 可以为空
     * @return top.tangyh.basic.base.R<java.util.List < java.lang.String>>
     * @author tangyh
     * @date 2022/11/18 2:25 PM
     * @create [2022/11/18 2:25 PM ] [tangyh] [初始创建]
     */
    @GetMapping("/anyone/findVisibleResource")
    R<List<String>> findVisibleResource(@RequestParam(value = "employeeId") Long employeeId,
                                        @RequestParam(value = "applicationId", required = false) Long applicationId);
}
