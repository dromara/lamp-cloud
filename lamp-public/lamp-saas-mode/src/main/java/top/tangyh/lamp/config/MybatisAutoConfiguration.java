package top.tangyh.lamp.config;


import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import top.tangyh.basic.database.datasource.BaseMybatisConfiguration;
import top.tangyh.basic.database.properties.DatabaseProperties;
import top.tangyh.basic.utils.SpringUtils;
import top.tangyh.lamp.common.api.UserApi;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置一些 Mybatis 常用重用拦截器
 *
 * @author zuihou
 * @date 2017-11-18 0:34
 */
@Configuration
@Slf4j
@EnableConfigurationProperties({DatabaseProperties.class})
public class MybatisAutoConfiguration extends BaseMybatisConfiguration {

    public MybatisAutoConfiguration(DatabaseProperties databaseProperties) {
        super(databaseProperties);
    }

    /**
     * 数据权限插件
     *
     * @return 数据权限插件
     */
    @Override
    protected List<InnerInterceptor> getPaginationBeforeInnerInterceptor() {
        List<InnerInterceptor> list = new ArrayList<>();
        return list;
    }
}
