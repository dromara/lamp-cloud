package com.tangyh.lamp.zuul.filter.pre;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.netflix.zuul.context.RequestContext;
import com.tangyh.basic.base.R;
import com.tangyh.basic.cache.model.CacheKey;
import com.tangyh.basic.cache.repository.CacheOps;
import com.tangyh.basic.context.ContextConstants;
import com.tangyh.basic.context.ContextUtil;
import com.tangyh.basic.exception.BizException;
import com.tangyh.basic.jwt.TokenUtil;
import com.tangyh.basic.jwt.model.AuthInfo;
import com.tangyh.basic.jwt.utils.JwtUtil;
import com.tangyh.basic.utils.StrPool;
import com.tangyh.lamp.common.cache.common.TokenUserIdCacheKeyBuilder;
import com.tangyh.lamp.common.constant.BizConstant;
import com.tangyh.lamp.common.properties.IgnoreProperties;
import com.tangyh.lamp.zuul.filter.BaseFilter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static com.tangyh.basic.context.ContextConstants.BASIC_HEADER_KEY;
import static com.tangyh.basic.context.ContextConstants.BEARER_HEADER_KEY;
import static com.tangyh.basic.context.ContextConstants.JWT_KEY_CLIENT_ID;
import static com.tangyh.basic.context.ContextConstants.JWT_KEY_TENANT;
import static com.tangyh.basic.exception.code.ExceptionCode.JWT_NOT_LOGIN;
import static com.tangyh.basic.exception.code.ExceptionCode.JWT_OFFLINE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 解析token中的用户信息 过滤器
 *
 * @author zuihou
 * @date 2017-12-13 15:22
 */
@Component
@Slf4j
@EnableConfigurationProperties({IgnoreProperties.class})
public class TokenContextFilter extends BaseFilter {
    private final TokenUtil tokenUtil;
    private final CacheOps cacheOps;
    @Value("${lamp.database.multiTenantType:SCHEMA}")
    protected String multiTenantType;

    public TokenContextFilter(IgnoreProperties ignoreProperties, RouteLocator routeLocator, TokenUtil tokenUtil, CacheOps cacheOps) {
        super(ignoreProperties, routeLocator);
        this.tokenUtil = tokenUtil;
        this.cacheOps = cacheOps;
    }


    /**
     * pre：可以在请求被路由之前调用
     * route：在路由请求时候被调用
     * post：在route和error过滤器之后被调用
     * error：处理请求时发生错误时被调用
     */
    @Override
    public String filterType() {
        // 前置过滤器
        return PRE_TYPE;
    }

    /**
     * filterOrder：通过int值来定义过滤器的执行顺序
     */
    @Override
    public int filterOrder() {
        /*
         * 一定要在 {@link org.springframework.cloud.netflix.zuul.filters.pre.PreDecorationFilter} 过滤器之后执行，因为这个过滤器做了路由  而我们需要这个路由信息来鉴权
         * 这个过滤器会将很多我们鉴权需要的信息放置在请求上下文中。故一定要在此过滤器之后执行
         */
        return FilterConstants.PRE_DECORATION_FILTER_ORDER + 1;
    }

    /**
     * 返回一个boolean类型来判断该过滤器是否要执行，所以通过此函数可实现过滤器的开关。在上例中，我们直接返回true，所以该过滤器总是生效
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑。需要注意，这里我们通过ctx.setSendZuulResponse(false)令zuul过滤该请求，
     * 不对其进行路由，然后通过ctx.setResponseStatusCode(200)设置了其返回的错误码
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        ContextUtil.setGrayVersion(getHeader(ContextConstants.GRAY_VERSION, request));

        try {
            //1, 解码 请求头中的租户信息
            parseTenant(ctx, request);

            // 2,解码 Authorization 后面完善
            parseClient(ctx, request);

            // 解析token
            parseToken(ctx, request);
        } catch (BizException e) {
            errorResponse(e.getMessage(), e.getCode(), 200);
        } catch (Exception e) {
            errorResponse("验证token出错", R.FAIL_CODE, 200);
        }
        return null;
    }

    private boolean parseToken(RequestContext ctx, HttpServletRequest request) {

        // 忽略 token 认证的接口
        if (isIgnoreToken()) {
            log.debug("access filter not execute");
            return true;
        }

        //获取token， 解析，然后想信息放入 header
        //3, 获取token
        String token = getHeader(BEARER_HEADER_KEY, request);

        AuthInfo authInfo;
        //添加测试环境的特殊token
        if (isDev(token)) {
            authInfo = new AuthInfo().setAccount("lamp").setUserId(2L)
                    .setTokenType(BEARER_HEADER_KEY).setName("超级管理员");
        } else {
            authInfo = tokenUtil.getAuthInfo(token);

            // 5，验证 是否在其他设备登录或被挤下线
            String newToken = JwtUtil.getToken(token);
            CacheKey cacheKey = new TokenUserIdCacheKeyBuilder().key(newToken);
            String tokenCache = cacheOps.get(cacheKey);

            if (StrUtil.isEmpty(tokenCache)) {
                errorResponse(JWT_NOT_LOGIN.getMsg(), JWT_NOT_LOGIN.getCode(), 200);
                return true;
            } else if (StrUtil.equals(BizConstant.LOGIN_STATUS, tokenCache)) {
                errorResponse(JWT_OFFLINE.getMsg(), JWT_OFFLINE.getCode(), 200);
                return true;
            }
        }

        //6, 转换，将 token 解析出来的用户身份 和 解码后的tenant、Authorization 重新封装到请求头
        if (authInfo != null) {
            addHeader(ctx, ContextConstants.JWT_KEY_ACCOUNT, authInfo.getAccount());
            addHeader(ctx, ContextConstants.JWT_KEY_USER_ID, authInfo.getUserId());
            addHeader(ctx, ContextConstants.JWT_KEY_NAME, authInfo.getName());
            MDC.put(ContextConstants.JWT_KEY_USER_ID, String.valueOf(authInfo.getUserId()));
        }
        return false;
    }

    private void parseClient(RequestContext ctx, HttpServletRequest request) {
        String base64Authorization = getHeader(BASIC_HEADER_KEY, request);
        if (StrUtil.isNotEmpty(base64Authorization)) {
            String[] client = JwtUtil.getClient(base64Authorization);
            ContextUtil.setClientId(client[0]);
            addHeader(ctx, JWT_KEY_CLIENT_ID, ContextUtil.getClientId());
        }
    }

    private void parseTenant(RequestContext ctx, HttpServletRequest request) {
        // 判断是否忽略tenant
        if ("NONE".equals(multiTenantType)) {
            addHeader(ctx, JWT_KEY_TENANT, StrPool.EMPTY);
            MDC.put(JWT_KEY_TENANT, StrPool.EMPTY);
            return;
        }

        if (isIgnoreTenant(request.getRequestURI())) {
            return;
        }

        String base64Tenant = getHeader(JWT_KEY_TENANT, request);
        if (StrUtil.isNotEmpty(base64Tenant)) {
            String tenant = JwtUtil.base64Decoder(base64Tenant);
            ContextUtil.setTenant(tenant);
            addHeader(ctx, JWT_KEY_TENANT, ContextUtil.getTenant());
            MDC.put(JWT_KEY_TENANT, ContextUtil.getTenant());
        }
    }

    /**
     * 忽略 租户编码
     */
    protected boolean isIgnoreTenant(String path) {
        return ignoreProperties.isIgnoreTenant(path);
    }

    private void addHeader(RequestContext ctx, String name, Object value) {
        if (value == null) {
            return;
        }
        String valueStr = value.toString();
        String valueEncode = URLUtil.encode(valueStr);
        ctx.addZuulRequestHeader(name, valueEncode);
    }

}

