package com.github.zuihou.center.shiro;

import com.github.zuihou.auth.utils.Token;
import com.github.zuihou.authority.api.AuthTokenApi;
import com.github.zuihou.authority.dto.auth.LoginDTO;
import com.github.zuihou.authority.entity.auth.User;
import com.github.zuihou.base.R;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ShiroAuthorizingRealm extends AuthorizingRealm {

    @Autowired
    private AuthTokenApi authTokenApi;

    /**
     * 构造函数，设置安全的初始化信息
     */
    public ShiroAuthorizingRealm() {
        super();
        System.out.println("--------------5---ShiroAuthorizingRealm-------------");
        setAuthenticationTokenClass(UsernamePasswordToken.class);
    }

    /**
     * 获取当前认证实体的授权信息（授权包括：角色、权限）
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取当前登录的用户名
        ShiroPrincipal subject = (ShiroPrincipal) super.getAvailablePrincipal(principals);
        String username = subject.getAccount();
        Long userId = subject.getUserId();
        log.info("用户【" + username + "】授权开始......");
        //try {
        //    if (!subject.isAuthorized()) {
        //        //根据用户名称，获取该用户所有的权限列表
        //        List<String> authorities = userConService.selectAuthoritiesName(userId);
        //        List<String> rolelist = userConService.selectRolesName(userId);
        //        subject.setAuthorities(authorities);
        //        subject.setRoles(rolelist);
        //        subject.setAuthorized(true);
        //        log.info("用户【" + username + "】授权初始化成功......");
        //        log.info("用户【" + username + "】 角色列表为：" + subject.getRoles());
        //        log.info("用户【" + username + "】 权限列表为：" + subject.getAuthorities());
        //    } else {
        //        if (subject.getRoles().isEmpty() || subject.getAuthorities().isEmpty()) {
        //            //根据用户名称，获取该用户所有的权限列表
        //            List<String> authorities = userConService.selectAuthoritiesName(userId);
        //            List<String> rolelist = userConService.selectRolesName(userId);
        //            subject.setAuthorities(authorities);
        //            subject.setRoles(rolelist);
        //        }
        //        log.info("用户【" + username + "】已授权......");
        //    }
        //} catch (RuntimeException e) {
        //    throw new AuthorizationException("用户【" + username + "】授权失败");
        //}
        //给当前用户设置权限
        //info.addStringPermissions(subject.getAuthorities());
        //info.addRoles(subject.getRoles());
        info.addRole("admin");
        return info;
    }

    /**
     * 根据认证方式（如表）获取用户名称、密码
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String username = upToken.getUsername();
        if (username == null) {
            log.warn("用户名不能为空");
            throw new AccountException("用户名不能为空");
        }

        String password = new String(upToken.getPassword());

        R<LoginDTO> result = authTokenApi.login(username, password);

        if (!result.getIsSuccess() && result.getData() != null) {
            log.warn("获取用户失败:{}", result.getMsg());
            throw new AccountException("账号或密码错误:" + result.getMsg());
        }
        LoginDTO data = result.getData();
        User user = data.getUser();
        Token token = data.getToken();

        ShiroPrincipal subject = new ShiroPrincipal();
        subject.setAccount(user.getAccount());
        subject.setUserId(user.getId());
        subject.setPhoto(user.getPhoto());
        subject.setNickName(user.getName());
        subject.setToken(token.getToken());
        return new SimpleAuthenticationInfo(subject, upToken.getPassword(), getName());
    }

    //@PostConstruct
    //public void initCredentialsMatcher() {
    //    HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(UserConService.HASH_ALGORITHM);
    //    matcher.setHashIterations(UserConService.HASH_INTERATIONS);
    //    setCredentialsMatcher(matcher);
    //}

}
