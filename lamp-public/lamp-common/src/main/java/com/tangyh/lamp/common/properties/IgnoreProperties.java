package com.tangyh.lamp.common.properties;

import cn.hutool.core.collection.CollUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * 忽略token 配置类
 * <p>
 * 做接口权限时，考虑修改成读取配置文件
 *
 * @author zuihou
 * @date 2019/01/03
 */
@Data
@ConfigurationProperties(prefix = IgnoreProperties.PREFIX)
public class IgnoreProperties {
    public static final String PREFIX = "lamp.ignore";
    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    private List<String> baseUri = CollUtil.newArrayList(
            "/**/*.css",
            "/**/*.js",
            "/**/*.html",
            "/**/*.ico",
            "/**/*.jpg",
            "/**/*.jpeg",
            "/**/*.png",
            "/**/*.gif",
            "/**/v2/**",
            "/**/api-docs/**",
            "/**/api-docs-ext/**",
            "/**/swagger-resources/**",
            "/**/webjars/**",
            "/actuator/**",
            "/error",
            "/**/anno/**",
            "/**/static/**"
    );

    private List<String> tenant = CollUtil.newArrayList("/**/noTenant/**");
    private List<String> token = CollUtil.newArrayList("/**/noToken/**", "/ds/**");

    public boolean isIgnoreToken(String path) {
        List<String> all = new ArrayList<>();
        all.addAll(getBaseUri());
        all.addAll(getToken());
        return all.stream().anyMatch(url -> path.startsWith(url) || ANT_PATH_MATCHER.match(url, path));
    }

    public boolean isIgnoreTenant(String path) {
        List<String> all = new ArrayList<>();
        all.addAll(getBaseUri());
        all.addAll(getTenant());
        return all.stream().anyMatch(url -> path.startsWith(url) || ANT_PATH_MATCHER.match(url, path));
    }


}
