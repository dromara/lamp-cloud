package top.tangyh.lamp.common.properties;

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
    /**
     * 是否启用权限校验
     *
     * @see 4.0.0
     */
    private Boolean authEnabled = false;

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
            "/**/static/**",
            "/**/public/**",
            "/error",
            // api 扫描
            "/*/systemApiScan",
            // 表单验证
            "/*/form/validator/**",
            // 不需要租户编码、不需要登录、不需要权限即可访问的接口
            "/**/anno/**"
    );
    /**
     * 不需要携带 租户ID, 也不校验是否登录（token）。  即： 请求头中不携带 tenant
     * <p>
     * 注意： 此类接口，无法操作租户库(base_0000、extend_0000)的数据，因为后台无法获取租户信息，导致无法切换数据库
     *
     * @see 3.x
     */
    private List<String> tenant = CollUtil.newArrayList("/**/noTenant/**", "/**/anyTenant/**");

    /**
     * 不需要登录, 且不需要校验权限
     * <p>
     * 注意： 此类接口，可以正常操作租户库(base_0000、extend_0000)的数据
     * 注意： 此类接口，无法获取当前请求的用户信息(userId)
     *
     * <p>
     * 如： 门户网站的接口，登录页面获取系统配置的接口等
     *
     * @see 3.x
     */
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
