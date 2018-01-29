package com.github.zuihou.file.rest.file.api;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author zuihou
 * @createTime 2018-01-26 23:01
 */
@FeignClient(name = "${zuihou.file.feign.server:zuihou-file-server}"/* , fallback = UploadApiHystrix.class */)
public interface FileApi {
}
