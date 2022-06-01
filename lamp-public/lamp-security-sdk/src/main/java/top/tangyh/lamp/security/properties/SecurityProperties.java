package top.tangyh.lamp.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import top.tangyh.basic.constant.Constants;

/**
 * 属性
 *
 * @author zuihou
 * @date 2020年02月24日10:48:35
 */
@Data
@ConfigurationProperties(prefix = SecurityProperties.PREFIX)
public class SecurityProperties {
    public static final String PREFIX = Constants.PROJECT_PREFIX + ".security";
    /**
     * 是否启用uri权限
     */
    private Boolean enabled = true;
    /**
     * 权限是否区分大小写
     */
    private Boolean caseSensitive = false;
}
