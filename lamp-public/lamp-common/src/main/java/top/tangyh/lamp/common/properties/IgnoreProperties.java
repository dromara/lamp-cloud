package top.tangyh.lamp.common.properties;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.AntPathMatcher;
import top.tangyh.basic.constant.Constants;
import top.tangyh.lamp.model.enumeration.HttpMethod;

import java.util.Map;
import java.util.Set;

import static top.tangyh.basic.utils.CollHelper.putAll;

/**
 * 忽略token 配置类
 * <p>
 * 1. 是否需要租户信息?
 * 2. 是否需要用户信息?
 * 3. 是否需要uri权限?
 *
 * @author zuihou
 * @date 2019/01/03
 */
@Data
@ConfigurationProperties(prefix = IgnoreProperties.PREFIX)
public class IgnoreProperties {
    public static final String PREFIX = Constants.PROJECT_PREFIX + ".ignore";
    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();
    /**
     * 是否启用网关的 uri权限鉴权 和 前端按钮权限 (设置为false，则不校验访问权限)
     *
     * @see 4.0.0
     */
    private Boolean authEnabled = true;
    /**
     * 前端校验按钮 是否区分大小写
     */
    private Boolean caseSensitive = false;

    private Map<String, Set<String>> baseUri = MapUtil.<String, Set<String>>builder(HttpMethod.ALL.name(), CollUtil.newHashSet(
            "/**/{p:[a-zA-Z0-9]+}.css",
            "/**/{p:[a-zA-Z0-9]+}.js",
            "/**/{p:[a-zA-Z0-9]+}.html",
            "/**/{p:[a-zA-Z0-9]+}.ico",
            "/**/{p:[a-zA-Z0-9]+}.jpg",
            "/**/{p:[a-zA-Z0-9]+}.jpeg",
            "/**/{p:[a-zA-Z0-9]+}.png",
            "/**/{p:[a-zA-Z0-9]+}.gif",
            "/**/v2/**",
            "/**/api-docs/**",
            "/**/api-docs-ext/**",
            "/**/swagger-resources/**",
            "/**/webjars/**",
            "/actuator/**",
            "/**/static/**",
            "/**/public/**",
            "/error",
            // 表单验证
            "/**/form/validator/**",
            // 不需要租户编码、不需要登录、不需要权限即可访问的接口
            "/**/anno/**",
            "/**/druid/**")).build();

    /**
     * 需要携带租户ID，需要登录，但无需验证是否拥有 uri 权限的接口。 即： 请求头中携带 tenant，也携带 token， 但不对uri权限验证
     * <p>
     * 注意： 此类接口，可以正常操作租户库(base_0000、extend_0000)的数据
     * 注意： 此类接口，可以获取当前请求的用户信息(userId)、员工信息(employeeId)
     * <p>
     * 如： 文件上传、字典查询等任何人都能访问的URI接口
     *
     * @see 4.0.0
     */
    private Map<String, Set<String>> anyone = MapUtil.newHashMap();


    /**
     * 需要携带租户ID，不需要登录, 且不需要校验权限。 即： 请求头中携带 tenant， 但不携带 token
     * <p>
     * 注意： 此类接口，可以正常操作租户库(base_0000、extend_0000)的数据
     * 注意： 此类接口，无法获取当前请求的用户信息(userId)、员工信息(employeeId)
     * 注意： 此类接口，无法获取控制URI权限
     * <p>
     * 如： 门户网站的接口，登录页面获取系统配置的接口等
     *
     * @see 4.0.0
     */
    private Map<String, Set<String>> anyUser = MapUtil.newHashMap();

    /**
     * 不需要携带 租户ID, 也不校验是否登录 和 是否有权限。  即： 请求头中不携带 tenant
     * <p>
     * 注意： 此类接口，无法操作租户库(base_0000、extend_0000)的数据，因为后台无法获取租户信息，导致无法切换数据库
     * 注意： 此类接口，无法获取当前请求的员工信息(employeeId)， 只能获取用户信息(userId)
     * 注意： 此类接口，无法获取控制URI权限
     * <p>
     * 如： 获取图片验证码、静态资源等接口
     *
     * @see 4.0.0
     */
    private Map<String, Set<String>> anyTenant = MapUtil.newHashMap();


    /**
     * 是否忽略uri权限认证
     *
     * @param path 相对路径
     * @return
     */
    public boolean isIgnoreUriAuth(String method, String path) {
        Map<String, Set<String>> all = putAll(getBaseUri(), this.getAnyTenant(), this.getAnyUser(), this.getAnyone());

        return isIgnore(method, path, all);
    }


    /**
     * 是否忽略登录
     *
     * @param path 相对路径
     * @return
     */
    public boolean isIgnoreUser(String method, String path) {
        Map<String, Set<String>> all = putAll(getBaseUri(), this.getAnyTenant(), this.getAnyUser());
        return isIgnore(method, path, all);
    }

    /**
     * 是否忽略租户信息
     *
     * @param path 相对路径
     * @return
     */
    public boolean isIgnoreTenant(String method, String path) {
        Map<String, Set<String>> all = putAll(getBaseUri(), this.getAnyTenant());
        return isIgnore(method, path, all);
    }

    private boolean isIgnore(String method, String path, Map<String, Set<String>> all) {
        for (Map.Entry<String, Set<String>> entry : all.entrySet()) {
            String m = entry.getKey();
            Set<String> paths = entry.getValue();
            if (HttpMethod.ALL.name().equalsIgnoreCase(m)) {
                return paths.stream().anyMatch(url -> ANT_PATH_MATCHER.match(url, path));
            } else {
                return m.equalsIgnoreCase(method) && paths.stream().anyMatch(url -> ANT_PATH_MATCHER.match(url, path));
            }
        }
        return false;
    }
}
