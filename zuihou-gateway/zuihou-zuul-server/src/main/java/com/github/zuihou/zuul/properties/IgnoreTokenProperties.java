package com.github.zuihou.zuul.properties;

import cn.hutool.core.collection.CollUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.AntPathMatcher;

import java.util.List;

import static com.github.zuihou.zuul.properties.IgnoreTokenProperties.PREFIX;

/**
 * 忽略token 配置类
 * <p>
 * 做接口权限时，考虑修改成读取配置文件
 *
 * @author zuihou
 * @date 2019/01/03
 */
@Data
@ConfigurationProperties(prefix = PREFIX)
public class IgnoreTokenProperties {
    public static final String PREFIX = "ignore.token";
    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();
    private List<String> url = CollUtil.newArrayList(
            "/error",
            "/actuator/**",
            "/gate/**",
            "/static/**",
            "/anno/**",
            "/**/anno/**",
            "/**/swagger-ui.html",
            "/**/doc.html",
            "/**/webjars/**",
            "/**/v2/api-docs/**",
            "/**/v2/api-docs-ext/**",
            "/**/swagger-resources/**"
    );

    public boolean isIgnoreToken(String path) {
        return getUrl().stream().anyMatch((url) -> path.startsWith(url) || ANT_PATH_MATCHER.match(url, path));
    }
}
