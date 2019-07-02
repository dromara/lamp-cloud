package com.github.zuihou.authority.api;

import com.github.zuihou.auth.utils.Token;
import com.github.zuihou.authority.api.hystrix.LogApiHystrix;
import com.github.zuihou.authority.entity.common.Log;
import com.github.zuihou.base.R;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2019/07/02
 */
@FeignClient(name = "${zuihou.feign.authority-server:zuihou-authority-server}", fallback = LogApiHystrix.class)
public interface LogApi {
    /**
     * 刷新token
     *
     * @param log 日志
     * @return
     */
    @RequestMapping(value = "/log", method = RequestMethod.POST)
    R<Token> save(@RequestBody Log log);

}
