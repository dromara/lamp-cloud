package top.tangyh.lamp.userinfo.resolver;

import cn.hutool.core.util.BooleanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import top.tangyh.basic.annotation.user.LoginUser;
import top.tangyh.basic.base.R;
import top.tangyh.basic.context.ContextUtil;
import top.tangyh.basic.utils.SpringUtils;
import top.tangyh.lamp.model.entity.base.SysUser;
import top.tangyh.lamp.userinfo.feign.UserQuery;
import top.tangyh.lamp.userinfo.feign.UserResolverService;

/**
 * Token转化SysUser
 *
 * @author zuihou
 * @date 2018/12/21
 */
@Slf4j
public class ContextArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * 入参筛选
     *
     * @param mp 参数集合
     * @return 格式化后的参数
     */
    @Override
    public boolean supportsParameter(MethodParameter mp) {
        return mp.hasParameterAnnotation(LoginUser.class) && mp.getParameterType().equals(SysUser.class);
    }

    /**
     * @param methodParameter       入参集合
     * @param modelAndViewContainer model 和 view
     * @param nativeWebRequest      web相关
     * @param webDataBinderFactory  入参解析
     * @return 包装对象
     */
    @Override
    public Object resolveArgument(@NonNull MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  @NonNull NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) {
        Long userId = ContextUtil.getUserId();
        String account = ContextUtil.getAccount();
        String name = ContextUtil.getName();

        //以下代码为 根据 @LoginUser 注解来注入 SysUser 对象
        SysUser user = SysUser.builder()
                .account(account)
                .name(name)
                .build();
        user.setId(userId);
        if (userId == null) {
            return user;
        }
        try {
            LoginUser loginUser = methodParameter.getParameterAnnotation(LoginUser.class);

            boolean isQuery = BooleanUtil.or(loginUser.isFull(), loginUser.isStation(), loginUser.isOrg(),
                    loginUser.isRoles(), loginUser.isResource());
            if (isQuery) {
                UserResolverService userResolverService = SpringUtils.getBean(UserResolverService.class);
                R<SysUser> result = userResolverService.getById(
                        UserQuery.builder()
                                .userId(userId)
                                .full(loginUser.isFull())
                                .org(loginUser.isOrg())
                                .station(loginUser.isStation())
                                .roles(loginUser.isRoles())
                                .resource(loginUser.isResource())
                                .build());
                if (result.getIsSuccess() && result.getData() != null) {
                    return result.getData();
                }
            }
        } catch (Exception e) {
            log.warn("注入登录人信息时，发生异常. --> {}", user, e);
        }

        return user;
    }

}
