package com.github.zuihou.authority.api;

import com.github.zuihou.authority.api.hystrix.RoleApiFallback;
import com.github.zuihou.base.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 角色API
 *
 * @author zuihou
 * @date 2019/08/02
 */
@FeignClient(name = "${zuihou.feign.authority-server:zuihou-authority-server}", path = "/role", fallback = RoleApiFallback.class)
public interface RoleApi {
    /**
     * 根据角色编码，查找用户id
     *
     * @param codes 角色编码
     * @return
     */
    @GetMapping("/codes")
    R<List<Long>> findUserIdByCode(@RequestParam(value = "codes") String[] codes);
}
