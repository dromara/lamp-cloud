package com.github.zuihou.eureka;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * 一定要有这个类， eureka 设置密码后，其他服务才能注册进来
 * 但同时也会因为这个类，导致验证密码的界面 消失， 需要研究一下
 *
 * @author tangyh
 * @date 2018/09/06
 */
@EnableWebSecurity
@Configuration
public class SecurityConf extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        //springcloud 2.x 后默认启用了 csrf 校验
//        http.csrf().disable();
////        http.cors().disable();
//        super.configure(http);
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
        http.csrf().disable();
        //注意：为了可以使用 http://${user}:${password}@${host}:${port}/eureka/ 这种方式登录,所以必须是httpBasic,如果是form方式,不能使用url格式登录
        http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
    }
//    @Bean
//    public UserDetailsService userDetailsService() {
//        User.UserBuilder builder = User.withDefaultPasswordEncoder();
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(builder.username("admin").password("admin").roles("USER").build());
//        return manager;
//    }
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
////                .passwordEncoder(new SystemPasswordEncoder())
//                //admin
//                .withUser("admin").password("admin").roles("EUREKA-CLIENT").and()
//                //eureka-security-client
//                .withUser("eureka-security-client").password("eureka-security-client").roles("EUREKA-CLIENT")
//        ;
//    }
}
