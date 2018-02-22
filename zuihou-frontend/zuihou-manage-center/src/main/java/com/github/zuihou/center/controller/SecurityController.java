package com.github.zuihou.center.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class SecurityController {
    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @PostMapping(value = "/login")
    public String login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password) {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);

        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException uae) {
            log.info("未知账户");
        } catch (IncorrectCredentialsException ice) {
            log.info("密码不正确");
        } catch (LockedAccountException lae) {
            log.info("账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            log.info("用户名密码次数过多");
        } catch (AuthenticationException ae) {
            log.info("用户名密码不正确");
        } catch (Exception e){
            log.error("登录失败", e);
        }

        if (subject.isAuthenticated()) {
            log.info("登录认证通过");
            return "redirect:/index";
        } else {
            usernamePasswordToken.clear();
            return "redirect:/login";
        }
    }

    @PostMapping(value = "/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "redirect:/login";
    }

    @GetMapping(value = "/unauthorized")
    public String unauthorized() {
        return "unauthorized";
    }
}
