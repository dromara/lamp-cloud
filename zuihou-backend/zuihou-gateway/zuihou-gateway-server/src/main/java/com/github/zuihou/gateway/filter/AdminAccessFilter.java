package com.github.zuihou.gateway.filter;

import com.github.zuihou.gateway.config.GateIgnoreProperties;
import com.github.zuihou.gateway.feign.LogService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

/**
 * admin 权限 过滤器
 *
 * @author zuihou
 * @createTime 2017-12-13 15:22
 */
@Component
@Slf4j
public class AdminAccessFilter extends ZuulFilter {

    @Autowired
    private LogService logService;
    /**
     * 网关忽略拦截前缀
     */
    //@Value("${gate.ignore.startWith}")
    //private String startWith;
    @Autowired
    private GateIgnoreProperties gateIgnoreProperties;

    /**
     * 为zuul设置一个公共的前缀
     */
    @Value("${zuul.prefix}")
    private String zuulPrefix;

    //@Autowired
    //private AppAuthConfig appAuthConfig;
    //@Autowired
    //private AppAuthUtil appAuthUtil;
    //@Autowired
    //private ServiceAuthConfig serviceAuthConfig;
    //@Autowired
    //private ServiceAuthUtil serviceAuthUtil;

    //@Autowired
    //private EurekaClient discoveryClient;

    /**
     * 给 userService 接口添加拦截器，每次调用userService 接口的方法，都自动在请求头中添加
     * auth-client-token 和 auth-user-token
     */
    @PostConstruct
    public void init() {
        //InstanceInfo prodSvcInfo = discoveryClient.getNextServerFromEureka("ZUIHOU-ADMIN-SERVER", false);
        //ServiceFeignInterceptor serviceFeignInterceptor = new ServiceFeignInterceptor();
        //serviceFeignInterceptor.setServiceAuthConfig(serviceAuthConfig);
        //serviceFeignInterceptor.setServiceAuthUtil(serviceAuthUtil);
        //serviceFeignInterceptor.setUserAuthConfig(appAuthConfig);
        //this.userService = Feign.builder().encoder(new JacksonEncoder())
        //        .decoder(new JacksonDecoder())
        //        .requestInterceptor(serviceFeignInterceptor)
        //        .target(UserService.class, prodSvcInfo.getHomePageUrl());
    }

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
        return "pre";
    }

    /**
     * filterOrder：通过int值来定义过滤器的执行顺序
     *
     * @return
     */
    @Override
    public int filterOrder() {
        // 优先级为1，数字越大，优先级越低
        return 1;
    }

    /**
     * 返回一个boolean类型来判断该过滤器是否要执行，所以通过此函数可实现过滤器的开关。在上例中，我们直接返回true，所以该过滤器总是生效
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        // 是否执行该过滤器，此处为true，说明需要过滤
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
        final String requestUri = request.getRequestURI().substring(zuulPrefix.length());
        final String method = request.getMethod();
        //BaseContextHandler.setToken(null);
        // 不进行拦截的地址
        if (isStartWith(requestUri)) {
            return null;
        }
        //IJWTInfo user = null;
        //try {
        //    user = getJWTUser(request, ctx);
        //} catch (Exception e) {
        //    if (e instanceof BizException) {
        //        setFailedRequest(JSONUtils.toJsonString(Result.fail(((BizException) e).getCode(), e.getMessage()), true), 200);
        //    } else {
        //        setFailedRequest(JSONUtils.toJsonString(Result.fail(ExceptionCode.SYSTEM_BUSY.getCode(), ExceptionCode.SYSTEM_BUSY.getMsg()), true), 200);
        //    }
        //    return null;
        //}

        //Result<Boolean> result = logService.check("aaaaa");
        //log.info(result.getData());

        ////所有的菜单，资源
        //List<PermissionInfo> permissionIfs = userService.getAllPermissionInfo();
        //// 判断资源是否启用权限约束
        //// 过滤后的资源
        //Stream<PermissionInfo> stream = getPermissionIfs(requestUri, method, permissionIfs);
        //List<PermissionInfo> result = stream.collect(Collectors.toList());
        //PermissionInfo[] permissions = result.toArray(new PermissionInfo[]{});
        //if (permissions.length > 0) {
        //    checkUserPermission(permissions, ctx, user);
        //}

        // 申请客户端密钥头
        //ctx.addZuulRequestHeader(CommonConstants.ZUUL_HEADER_KEY_APP_ID, user.getAppId());
        //ctx.addZuulRequestHeader(serviceAuthConfig.getTokenHeader(), serviceAuthUtil.getClientToken());
        //BaseContextHandler.remove();
        return null;
    }

    /**
     * 获取目标权限资源
     *
     * @param requestUri
     * @param method
     * @param serviceInfo
     * @return
     */
    //private Stream<PermissionInfo> getPermissionIfs(final String requestUri, final String method, List<PermissionInfo> serviceInfo) {
    //    return serviceInfo.parallelStream().filter(new Predicate<PermissionInfo>() {
    //        @Override
    //        public boolean test(PermissionInfo permissionInfo) {
    //            String url = permissionInfo.getUri();
    //            String uri = url.replaceAll("\\{\\*\\}", "[a-zA-Z\\\\d]+");
    //            String regEx = "^" + uri + "$";
    //            return (Pattern.compile(regEx).matcher(requestUri).find() || requestUri.startsWith(url + "/"))
    //                    && method.equals(permissionInfo.getMethod());
    //        }
    //    });
    //}

    //private void setCurrentUserInfoAndLog(RequestContext ctx, IJWTInfo user, PermissionInfo pm) {
    //    String host = ClientUtil.getClientIp(ctx.getRequest());
    //    ctx.addZuulRequestHeader(CommonConstants.JWT_KEY_ADMIN_ID, String.valueOf(user.getAdminId()));
    //    ctx.addZuulRequestHeader(CommonConstants.JWT_KEY_NAME, URLEncoder.encode(user.getName()));
    //    ctx.addZuulRequestHeader(CommonConstants.JWT_KEY_APP_ID, user.getAppId());
    //    ctx.addZuulRequestHeader(CommonConstants.JWT_KEY_USER_NAME, user.getUserName());
    //    ctx.addZuulRequestHeader(CommonConstants.JWT_KEY_HOST_ID, ClientUtil.getClientIp(ctx.getRequest()));
    //    LogDto logInfo = new LogDto(pm.getMenu(), pm.getName(), pm.getUri(),
    //            "params",
    //            new Date(), user.getAdminId(), user.getName(), host);
    //    DBLog.getInstance().setLogService(logService).offerQueue(logInfo);
    //}

    /**
     * 返回session中的用户信息
     *
     * @param request
     * @param ctx
     * @return
     */
    //private IJWTInfo getJWTUser(HttpServletRequest request, RequestContext ctx) throws BizException {
    //    String isAdmin = request.getHeader("isAdmin");
    //    if ("true".equals(isAdmin)) {
    //        String authToken = request.getHeader(serviceAuthConfig.getTokenHeader());
    //        if (StringUtils.isBlank(authToken)) {
    //            authToken = request.getParameter(serviceAuthConfig.getTokenHeader());
    //        }
    //        ctx.addZuulRequestHeader(serviceAuthConfig.getTokenHeader(), authToken);
    //        return serviceAuthUtil.getInfoFromToken(authToken);
    //    } else {
    //        String authToken = request.getHeader(appAuthConfig.getTokenHeader());
    //        if (StringUtils.isBlank(authToken)) {
    //            authToken = request.getParameter(appAuthConfig.getTokenHeader());
    //        }
    //        ctx.addZuulRequestHeader(appAuthConfig.getTokenHeader(), authToken);
    //        //BaseContextHandler.setToken(authToken);
    //        return appAuthUtil.getInfoFromToken(authToken);
    //    }
    //}


    /**
     * 检查用户权限
     *
     * @param permissions 所有的资源权限
     * @param ctx
     * @param user
     */
    //private void checkUserPermission(PermissionInfo[] permissions, RequestContext ctx, IJWTInfo user) {
    //    //用户拥有的资源权限
    //    List<PermissionInfo> permissionInfos = userService.getPermissionByUsername(user.getUniqueName());
    //    PermissionInfo current = null;
    //    for (PermissionInfo info : permissions) {
    //        boolean anyMatch = permissionInfos.parallelStream().anyMatch(new Predicate<PermissionInfo>() {
    //            @Override
    //            public boolean test(PermissionInfo permissionInfo) {
    //                return permissionInfo.getCode().equals(info.getCode());
    //            }
    //        });
    //        if (anyMatch) {
    //            current = info;
    //            break;
    //        }
    //    }
    //    if (current == null) {
    //        //权限不够
    //        setFailedRequest(JSON.toJSONString(new TokenForbiddenResponse("Token Forbidden!")), 200);
    //    } else {
    //        if (!RequestMethod.GET.toString().equals(current.getMethod())) {
    //            setCurrentUserInfoAndLog(ctx, user, current);
    //        }
    //    }
    //}


    /**
     * URI是否以什么打头
     *
     * @param requestUri
     * @return
     */
    private boolean isStartWith(String requestUri) {
        boolean flag = false;
        //TODO 实现正则匹配
        if (gateIgnoreProperties == null || gateIgnoreProperties.getStartWithList().isEmpty()) {
            return flag;
        }
        for (String s : gateIgnoreProperties.getStartWithList()) {
            if (requestUri.startsWith(s)) {
                return true;
            }
        }
        return flag;
    }

    /**
     * 网关抛异常
     *
     * @param body
     * @param code
     */
    private void setFailedRequest(String body, int code) {
        log.debug("Reporting error ({}): {}", code, body);
        RequestContext ctx = RequestContext.getCurrentContext();
        // 返回错误码
        ctx.setResponseStatusCode(code);
        if (ctx.getResponseBody() == null) {
            // 返回错误内容
            ctx.setResponseBody(body);
            // 过滤该请求，不对其进行路由
            ctx.setSendZuulResponse(false);
        }
    }

}

