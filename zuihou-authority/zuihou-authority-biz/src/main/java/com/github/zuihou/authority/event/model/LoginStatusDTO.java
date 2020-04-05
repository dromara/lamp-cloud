package com.github.zuihou.authority.event.model;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.github.zuihou.authority.entity.auth.UserToken;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.log.util.AddressUtil;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * 登录状态DTO
 *
 * @author zuihou
 * @date 2020年03月18日17:25:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
public class LoginStatusDTO implements Serializable {
    private static final long serialVersionUID = -3124612657759050173L;
    /***
     * 用户id
     */
    private Long id;
    /**
     * 账号
     */
    private String account;
    /**
     * 租户编码
     */
    private String tenant;
    /**
     * 登录类型
     */
    private Type type;
    /**
     * 登录描述
     */
    private String description;

    /**
     * 登录浏览器
     */
    private String ua;
    /**
     * 登录IP
     */
    private String ip;
    /**
     * 登录地址
     */
    private String location;

    private UserToken userToken;

    public static LoginStatusDTO success(Long id, UserToken userToken) {
        LoginStatusDTO loginStatus = LoginStatusDTO.builder()
                .id(id).tenant(BaseContextHandler.getTenant())
                .type(Type.SUCCESS).description("登录成功")
                .build().setInfo();
        userToken.setLoginIp(loginStatus.getIp());
        userToken.setLocation(loginStatus.getLocation());
        loginStatus.setUserToken(userToken);
        return loginStatus;
    }

    public static LoginStatusDTO fail(Long id, String description) {
        return LoginStatusDTO.builder()
                .id(id).tenant(BaseContextHandler.getTenant())
                .type(Type.FAIL).description(description)
                .build().setInfo();
    }

    public static LoginStatusDTO fail(String account, String description) {
        return LoginStatusDTO.builder()
                .account(account).tenant(BaseContextHandler.getTenant())
                .type(Type.FAIL).description(description)
                .build().setInfo();
    }

    public static LoginStatusDTO pwdError(Long id, String description) {
        return LoginStatusDTO.builder()
                .id(id).tenant(BaseContextHandler.getTenant())
                .type(Type.PWD_ERROR).description(description)
                .build().setInfo();
    }

    private LoginStatusDTO setInfo() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return this;
        }
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        if (request == null) {
            return this;
        }
        String ua = StrUtil.sub(request.getHeader("user-agent"), 0, 500);
        String ip = ServletUtil.getClientIP(request);
        String location = AddressUtil.getRegion(ip);
        this.ua = ua;
        this.ip = ip;
        this.location = location;
        return this;
    }

    @Getter
    public enum Type {
        SUCCESS,
        PWD_ERROR,
        FAIL;
    }

}
