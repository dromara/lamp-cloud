//package com.github.zuihou.scan.listener;
//
//
//import com.github.zuihou.scan.model.SystemApiScanSaveDTO;
//import com.github.zuihou.scan.service.SystemApiService;
//import com.github.zuihou.scan.utils.RequestMappingScanUtils;
//import com.github.zuihou.utils.SpringUtils;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.core.env.Environment;
//import org.springframework.util.AntPathMatcher;
//import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
//
///**
// * 资源扫描
// * 监听项目启动后执行
// *
// * @author zuihou
// * @date 2019/12/15
// */
//@Slf4j
//public class RequestMappingScan implements ApplicationListener<ApplicationReadyEvent> {
//    private static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
//
//    private final SystemApiService systemApiService;
//
//    public RequestMappingScan(SystemApiService systemApiService) {
//        this.systemApiService = systemApiService;
//    }
//
//    /**
//     * 初始化方法
//     *
//     * @param event
//     */
//    @Override
//    public void onApplicationEvent(ApplicationReadyEvent event) {
//        ConfigurableApplicationContext applicationContext = event.getApplicationContext();
//        Environment env = applicationContext.getEnvironment();
//        // 服务名称
//        String serviceId = env.getProperty("spring.application.name", "application");
//        RequestMappingHandlerMapping mapping = SpringUtils.getBean(RequestMappingHandlerMapping.class);
//        log.info("ApplicationReadyEvent:[{}]", serviceId);
//        // 需要想法拿到租户编码
//        SystemApiScanSaveDTO scan = RequestMappingScanUtils.scan(serviceId, mapping);
//
//
//        systemApiService.batchSave(scan);
//    }
//
//
//}
