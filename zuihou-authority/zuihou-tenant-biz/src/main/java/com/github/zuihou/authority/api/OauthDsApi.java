package com.github.zuihou.authority.api;

import com.github.zuihou.authority.api.fallback.OauthDsApiFallback;
import com.github.zuihou.base.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 权限服务初始化数据源
 *
 * @author zuihou
 * @date 2020年04月05日18:18:26
 */
@FeignClient(name = "${zuihou.feign.oauth-server:zuihou-oauth-server}", path = "/ds", fallback = OauthDsApiFallback.class)
public interface OauthDsApi {
    /**
     * 初始化数据源
     *
     * @param tenant
     * @return
     */
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    R<Boolean> init(@RequestParam(value = "tenant") String tenant);

    /**
     * 删除数据源
     *
     * @param tenant
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    R<Boolean> remove(@RequestParam(value = "tenant") String tenant);
}
