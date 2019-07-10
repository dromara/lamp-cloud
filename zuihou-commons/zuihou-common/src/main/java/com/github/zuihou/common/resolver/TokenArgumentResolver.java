package com.github.zuihou.common.resolver;

import javax.servlet.http.HttpServletRequest;

import com.github.zuihou.base.R;
import com.github.zuihou.common.annotation.LoginUser;
import com.github.zuihou.common.feign.UserQuery;
import com.github.zuihou.common.feign.UserResolveApi;
import com.github.zuihou.common.model.SysUser;
import com.github.zuihou.context.BaseContextConstants;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.utils.NumberHelper;
import com.github.zuihou.utils.StringHelper;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Token转化SysUser
 *
 * @author zlt
 * @date 2018/12/21
 */
@Slf4j
public class TokenArgumentResolver implements HandlerMethodArgumentResolver {

    private UserResolveApi userResolveApi;

    public TokenArgumentResolver(UserResolveApi userResolveApi) {
        this.userResolveApi = userResolveApi;
    }

    /**
     * 入参筛选
     *
     * @param methodParameter 参数集合
     * @return 格式化后的参数
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return true;
    }

    /**
     * @param methodParameter       入参集合
     * @param modelAndViewContainer model 和 view
     * @param nativeWebRequest      web相关
     * @param webDataBinderFactory  入参解析
     * @return 包装对象
     */
    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        String userId = getHeader(request, BaseContextConstants.JWT_KEY_USER_ID);
        String account = getHeader(request, BaseContextConstants.JWT_KEY_ACCOUNT);
        String name = getHeader(request, BaseContextConstants.JWT_KEY_NAME);
        String orgId = getHeader(request, BaseContextConstants.JWT_KEY_ORG_ID);
        String stationId = getHeader(request, BaseContextConstants.JWT_KEY_STATION_ID);
        BaseContextHandler.setUserId(userId);
        BaseContextHandler.setAccount(account);
        BaseContextHandler.setName(name);
        BaseContextHandler.setOrgId(orgId);
        BaseContextHandler.setStationId(stationId);

        //以下代码为 根据 @LoginUser 注解来注入 SysUser 对象
        if (!(methodParameter.hasParameterAnnotation(LoginUser.class) && methodParameter.getParameterType().equals(SysUser.class))) {
            return null;
        }

        SysUser user = SysUser.builder()
                .id(NumberHelper.longValueOf0(userId))
                .account(account)
                .name(name)
                .orgId(NumberHelper.longValueOf0(orgId))
                .stationId(NumberHelper.longValueOf0(stationId))
                .build();

        LoginUser loginUser = methodParameter.getParameterAnnotation(LoginUser.class);
        boolean isFull = loginUser.isFull();

        if (isFull || loginUser.isStation() || loginUser.isOrg() || loginUser.isRoles()) {
            R<SysUser> result = userResolveApi.getById(NumberHelper.longValueOf0(userId),
                    UserQuery.builder()
                            .full(isFull)
                            .org(loginUser.isOrg())
                            .station(loginUser.isStation())
                            .roles(loginUser.isRoles())
                            .build());
            if (result.getIsSuccess() && result.getData() != null) {
                return result.getData();
            }
        }

        return user;
    }

    private String getHeader(HttpServletRequest request, String name) {
        String value = request.getHeader(name);
        if (StrUtil.isEmpty(value)) {
            return null;
        }
        return StringHelper.decode(value);
    }
}
