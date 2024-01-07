package top.tangyh.lamp.common.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import top.tangyh.basic.constant.Constants;

/**
 * 登录配置
 *
 * @author zuihou
 * @date 2021/1/28 7:57 下午
 */
@Setter
@Getter
@RefreshScope
@ConfigurationProperties(prefix = SystemProperties.PREFIX)
public class SystemProperties {
    public static final String PREFIX = Constants.PROJECT_PREFIX + ".system";
    /**
     * 登录时 是否验证密码， 开发环境使用
     */
    private Boolean verifyPassword = true;
    /**
     * 登录时 是否验证验证码， 开发环境使用
     */
    private Boolean verifyCaptcha = true;
    /**
     * 密码最大输错次数  小于0不限制
     */
    private Integer maxPasswordErrorNum = 10;
    /**
     * 默认用户密码
     */
    private String defPwd = "123456";
    /**
     * 密码错误锁定用户时间
     * <p>
     * 0: 今天结束
     * 1m: 1分钟后
     * 1h: 1小时后
     * 4d: 4天后
     * 2w: 2周后
     * 3M: 3个月后
     * 5y: 5年后
     */
    private String passwordErrorLockUserTime = "0";

    /**
     * 记录 cloud 或 boot 项目所有方法的日志
     */
    private Boolean recordLamp = false;
    /**
     * 记录 cloud 或 boot 项目所有方法的入参
     */
    private Boolean recordLampArgs = true;
    /**
     * 记录 cloud 或 boot 项目所有方法的返回值
     */
    private Boolean recordLampResult = true;

    /**
     * 缓存Key前缀
     *
     * @author tangyh
     * @date 2023/5/15 9:28 AM
     * @create [2023/5/15 9:28 AM ] [tangyh] [初始创建]
     */
    private String cachePrefix;
    /** oauth 服务扫描枚举类的包路径 */
    private String enumPackage;
}
