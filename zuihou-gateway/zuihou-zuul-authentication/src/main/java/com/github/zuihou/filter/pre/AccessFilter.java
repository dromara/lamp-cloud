package com.github.zuihou.filter.pre;

import com.github.zuihou.filter.BaseFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 权限验证过滤器
 *
 * @author zuihou
 * @date 2019/07/31
 */
public class AccessFilter extends BaseFilter {
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER + 10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 验证当前用户 是否拥有某个URI 的访问权限
     * <p>
     * eurekaCode + uri + method + userId
     * <p>
     * <p>
     * 数据库存了 指定人某有某些权限
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {

        return null;
    }
}
