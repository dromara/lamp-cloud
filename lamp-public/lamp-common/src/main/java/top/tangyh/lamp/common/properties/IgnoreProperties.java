package top.tangyh.lamp.common.properties;

import cn.hutool.core.collection.CollUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
     * 不需要携带 租户编码, 也无法校验权限
     *
     * @see 3.x
     */
    private List<String> tenant = CollUtil.newArrayList("/**/noTenant/**", "/**/anyTenant/**");

    /**
     * 不需要登录, 且不需要校验权限
     *
     * @see 3.x
     */
    private List<String> token = CollUtil.newArrayList("/**/noToken/**", "/ds/**");
//    private List<String> login = CollUtil.newArrayList("/**/anyUser/**", "/ds/**");

    /**
     * 需要登录，但忽略权限的接口
     *
     * @see 4.0.0
     */
    private List<String> auth = CollUtil.newArrayList("/**/anyone/**");

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


    /**
     * 是否忽略权限
     *
     * @param path 相对路径
     * @return
     */
    public boolean isIgnoreAuth(String path) {
        Set<String> all = new HashSet<>();
        all.addAll(getBaseUri());
        all.addAll(getToken());
        all.addAll(getAuth());
        return all.stream().anyMatch(url -> path.startsWith(url) || ANT_PATH_MATCHER.match(url, path));
    }


}
