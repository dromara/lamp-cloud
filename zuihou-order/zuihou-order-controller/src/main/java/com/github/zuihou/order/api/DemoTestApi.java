package com.github.zuihou.order.api;

import com.github.zuihou.authority.entity.core.Org;
import com.github.zuihou.base.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2019/08/12
 */
@FeignClient(name = "${zuihou.feign.authority-server:zuihou-demo-server}", path = "/seata")
public interface DemoTestApi {
    /**
     * 新增时发生异常
     *
     * @param data
     * @return
     */
    @PostMapping("/saveEx")
    R<Org> saveEx(@RequestBody Org data);

    /**
     * 新增
     *
     * @param data
     * @return
     */
    @PostMapping("/save")
    R<Org> save(@RequestBody Org data);
}
