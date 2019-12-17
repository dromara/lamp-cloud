package com.github.zuihou.scan.feign;

import com.github.zuihou.base.R;
import com.github.zuihou.scan.model.SystemApiScanSaveDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 系统接口
 *
 * @author zuihou
 * @date 2019/12/16
 */
@FeignClient(name = "${zuihou.feign.authority-server:zuihou-authority-server}", path = "/systemApi", fallback = SystemApiApiFallback.class)
public interface SystemApiApi {
    /**
     * 批量保存
     *
     * @param data
     * @return
     */
    @PostMapping("/batch")
    R<Boolean> batchSave(@RequestBody SystemApiScanSaveDTO data);

}
