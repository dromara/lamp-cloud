package com.github.zuihou.file.rest.file.api;

import com.github.zuihou.base.Result;
import com.github.zuihou.file.rest.file.api.hystrix.UploadApiHystrix;
import com.github.zuihou.file.rest.file.dto.UploadListDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@FeignClient(name = "${zuihou.file.feign.server:zuihou-file-server}", fallback = UploadApiHystrix.class)
public interface UploadApi {

    /**
     * 多文件上传
     * @param request
     * @return
     */
    @RequestMapping(value = "/upload/multi", method = RequestMethod.POST)
    Result<UploadListDto> uploadMulti(HttpServletRequest request);

}
