package com.github.zuihou.gateway.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * TODO 日志接口 待实现
 *
 * @author zuihou
 * @createTime 2017-12-13 15:09
 */
@FeignClient("zuihou-admin-server")
public interface LogService {
    @RequestMapping(value = "/api/log/save", method = RequestMethod.POST)
    void saveLog(LogDto log);
}
