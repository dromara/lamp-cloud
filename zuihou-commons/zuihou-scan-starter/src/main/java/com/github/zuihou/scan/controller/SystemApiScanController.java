package com.github.zuihou.scan.controller;

import com.github.zuihou.base.R;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.scan.model.SystemApiScanSaveDTO;
import com.github.zuihou.scan.service.SystemApiScanService;
import com.github.zuihou.scan.utils.RequestMappingScanUtils;
import com.github.zuihou.utils.SpringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * 系统api扫描
 *
 * @author zuihou
 * @date 2019/12/16
 */
@RestController
@Slf4j
@RequestMapping("/systemApiScan")
@Api(value = "SystemApiScan", tags = "系统api")
public class SystemApiScanController {

    private final SystemApiScanService systemApiService;


    @Autowired
    public SystemApiScanController(@Qualifier("systemApiScanService") SystemApiScanService systemApiService) {
        this.systemApiService = systemApiService;
    }

    /**
     * 手动扫描
     *
     * @return 查询结果
     */
    @ApiOperation(value = "查询全局账号", notes = "查询全局账号")
    @GetMapping
    public R<Boolean> scan() {
        ApplicationContext applicationContext = SpringUtils.getApplicationContext();
        Environment env = applicationContext.getEnvironment();
        // 服务名称
        String serviceId = env.getProperty("spring.application.name", "application");
        RequestMappingHandlerMapping mapping = SpringUtils.getBean(RequestMappingHandlerMapping.class);
        log.info("ApplicationReadyEvent:[{}]", serviceId);

        SystemApiScanSaveDTO scan = RequestMappingScanUtils.scan(serviceId, mapping);

        scan.setTenant(BaseContextHandler.getTenant());
        return R.success(systemApiService.batchSave(scan));
    }


}
