package top.tangyh.lamp.gateway.filter;

import org.springframework.core.Ordered;

/**
 * @author zuihou
 * @date 2021/12/10 0:03
 */
public interface OrderedConstant {
    /**
     * 调用链
     */
    int TRACE = Ordered.HIGHEST_PRECEDENCE;
    /**
     * 解析token
     */
    int TOKEN = -1000;
    /**
     * uri权限
     */
    int AUTHENTICATION = -500;
    int SWAGGER = 1;
    int GRAY = 10150;
}
