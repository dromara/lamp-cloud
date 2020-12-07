//package com.tangyh.lamp.monitor.configurer;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * 配置HTTP BASIC权限验证
// *
// * @author zuihou
// * @date 2018-01-04 16:03
// */
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, proxyTargetClass = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    public void configure(WebSecurity web) {
//        //忽略css.jq.img等文件
//        web.ignoring().antMatchers("/**.html", "/**.css", "/img/**", "/**.js", "/third-party/**");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable() //HTTP with Disable CSRF
//                .authorizeRequests() //Authorize Request Configuration
//                .antMatchers("/login",
//                        "/api/**",
//                        //"/mgmt/**",
//                        "/**/heapdump",
//                        "/**/loggers",
//                        "/**/liquibase",
//                        "/**/logfile",
//                        "/**/flyway",
//                        "/**/auditevents",
//                        "/**/jolokia").permitAll() //放开"/api/**"：为了给被监控端免登录注册并解决Log与Logger冲突
//                .and()
//                .authorizeRequests()
//                .antMatchers("/**").hasRole("USER")
//                .antMatchers("/**").authenticated()
//                .and() //Login Form configuration for all others
//                .formLogin()
//                .loginPage("/login.html")
//                .loginProcessingUrl("/login").permitAll()
//                .defaultSuccessUrl("/")
//                .and() //Logout Form configuration
//                .logout()
//                .deleteCookies("remove")
//                .logoutSuccessUrl("/login.html").permitAll()
//                .and()
//                .httpBasic();
//    }
//    //@Override
//    //protected void configure(HttpSecurity http) throws Exception {
//    //    // Page with login form is served as /login.html and does a POST on /login
//    //    http.formLogin().loginPage("/login.html").loginProcessingUrl("/login").permitAll();
//    //    // The UI does a POST on /logout on logout
//    //    http.logout().logoutUrl("/logout");
//    //    // The ui currently doesn't support csrf
//    //    http.csrf().disable();
//    //
//    //    // Requests for the login page and the static assets are allowed
//    //    http.authorizeRequests()
//    //            .antMatchers("/login.html", "/**/*.css", "/img/**", "/third-party/**")
//    //            .permitAll();
//    //    // ... and any other request needs to be authorized
//    //    http.authorizeRequests().antMatchers("/**").authenticated();
//    //
//    //    // Enable so that the clients can authenticate via HTTP basic for registering
//    //    http.httpBasic();
//    //}
//}
