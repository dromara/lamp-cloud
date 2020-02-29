package com.github.zuihou.zuul.filter.pre;

import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.github.zuihou.auth.client.properties.AuthClientProperties;
import com.github.zuihou.auth.client.utils.JwtTokenClientUtils;
import com.github.zuihou.auth.utils.JwtUserInfo;
import com.github.zuihou.base.R;
import com.github.zuihou.context.BaseContextConstants;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.exception.BizException;
import com.github.zuihou.filter.BaseFilter;
import com.github.zuihou.utils.StrPool;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 解析token中的用户信息 过滤器
 *
 * @author zuihou
 * @createTime 2017-12-13 15:22
 */
@Component
@Slf4j
public class TokenContextFilter extends BaseFilter {

    @Autowired
    private AuthClientProperties authClientProperties;
    @Autowired
    private JwtTokenClientUtils jwtTokenClientUtils;

    /**
     * pre：可以在请求被路由之前调用
     * route：在路由请求时候被调用
     * post：在route和error过滤器之后被调用
     * error：处理请求时发生错误时被调用
     *
     * @return
     */
    @Override
    public String filterType() {
        // 前置过滤器
        return PRE_TYPE;
    }

    /**
     * filterOrder：通过int值来定义过滤器的执行顺序
     *
     * @return
     */
    @Override
    public int filterOrder() {
        // 数字越大，优先级越低
        /**
         * 一定要在 {@link org.springframework.cloud.netflix.zuul.filters.pre.PreDecorationFilter} 过滤器之后执行，因为这个过滤器做了路由  而我们需要这个路由信息来鉴权
         * 这个过滤器会将很多我们鉴权需要的信息放置在请求上下文中。故一定要在此过滤器之后执行
         */
        return FilterConstants.PRE_DECORATION_FILTER_ORDER + 1;
    }

    /**
     * 返回一个boolean类型来判断该过滤器是否要执行，所以通过此函数可实现过滤器的开关。在上例中，我们直接返回true，所以该过滤器总是生效
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑。需要注意，这里我们通过ctx.setSendZuulResponse(false)令zuul过滤该请求，
     * 不对其进行路由，然后通过ctx.setResponseStatusCode(200)设置了其返回的错误码
     *
     * @return
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String clientIP = ServletUtil.getClientIP(request);
        log.info("clientIP={}", clientIP);

        BaseContextHandler.setGrayVersion(getHeader(BaseContextConstants.GRAY_VERSION, request));

        // 不进行拦截的地址
        if (isIgnoreToken()) {
            log.debug("access filter not execute");
            return null;
        }
        //获取token， 解析，然后想信息放入 heade
        //1, 获取token
        String userToken = getHeader(authClientProperties.getUser().getHeaderName(), request);

        //2, 解析token
        JwtUserInfo userInfo = null;

        //添加测试环境的特殊token
        if (isDev() && StrPool.TEST.equalsIgnoreCase(userToken)) {
            userInfo = new JwtUserInfo(1L, "zuihou", "最后");
        }
        try {
            if (!isIgnoreToken() && userInfo == null) {
                userInfo = jwtTokenClientUtils.getUserInfo(userToken);
            }
        } catch (BizException e) {
            errorResponse(e.getMessage(), e.getCode(), 200);
            return null;
        } catch (Exception e) {
            errorResponse("验证token出错", R.FAIL_CODE, 200);
            return null;
        }

        //3, 将信息放入header
        if (userInfo != null) {
            addHeader(ctx, BaseContextConstants.JWT_KEY_ACCOUNT, userInfo.getAccount());
            addHeader(ctx, BaseContextConstants.JWT_KEY_USER_ID, userInfo.getUserId());
            addHeader(ctx, BaseContextConstants.JWT_KEY_NAME, userInfo.getName());

        }

        log.info("userInfo={}", userInfo);
        return null;
    }

    private void addHeader(RequestContext ctx, String name, Object value) {
        if (StringUtils.isEmpty(value)) {
            return;
        }
        String valueStr = value.toString();
        String valueEncode = URLUtil.encode(valueStr);
        ctx.addZuulRequestHeader(name, valueEncode);
    }

}

