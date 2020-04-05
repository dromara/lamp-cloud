package com.github.zuihou.authority.context;

import com.github.zuihou.authority.service.defaults.InitSystemContext;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * 初始化数据源
 * <p>
 * 一定要在容器初始化完成后，在初始化租户数据源
 * <p>
 * 使用 @PostConstruct 注解不行
 *
 * @author zuihou
 * @date 2020年03月15日13:12:59
 */
@AllArgsConstructor
public class InitDatabaseOnStarted implements ApplicationListener<ApplicationStartedEvent> {

    private InitSystemContext initSystemContext;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        initSystemContext.initDataSource();
    }
}
