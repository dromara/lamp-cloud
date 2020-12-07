package com.tangyh.lamp.oauth.api;

import com.tangyh.basic.base.R;
import com.tangyh.basic.log.entity.OptLogDTO;
import com.tangyh.lamp.oauth.api.hystrix.LogApiHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 操作日志保存 API
 *
 * @author zuihou
 * @date 2019/07/02
 */
@FeignClient(name = "${lamp.feign.oauth-server:lamp-oauth-server}", fallback = LogApiHystrix.class, qualifier = "logApi")
public interface LogApi {

    /**
     * 保存日志
     *
     * @param log 操作日志
     * @return 操作日志
     */
    @RequestMapping(value = "/optLog", method = RequestMethod.POST)
    R<OptLogDTO> save(@RequestBody OptLogDTO log);

}
