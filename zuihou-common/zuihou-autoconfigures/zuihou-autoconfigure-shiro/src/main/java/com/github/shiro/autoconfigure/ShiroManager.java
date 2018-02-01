/*
 *    Copyright 2010-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.github.shiro.autoconfigure;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * Shiro Config Manager.
 * <p>
 * Spring框架还提供了很多@Condition给我们用，当然总结用语哪种好理解，看给位读者喽
 *
 * @Conditional(TestCondition.class) :
 * 这句代码可以标注在类上面，表示该类下面的所有@Bean都会启用配置，也可以标注在方法上面，只是对该方法启用配置。
 * @ConditionalOnBean（仅仅在当前上下文中存在某个对象时，才会实例化一个Bean）
 * @ConditionalOnClass（某个class位于类路径上，才会实例化一个Bean）
 * @ConditionalOnExpression（当表达式为true的时候，才会实例化一个Bean）
 * @ConditionalOnMissingBean（仅仅在当前上下文中不存在某个对象时，才会实例化一个Bean）
 * @ConditionalOnMissingClass（某个class类路径上不存在的时候，才会实例化一个Bean）
 * @ConditionalOnNotWebApplication（不是web应用）
 * @ConditionalOnClass：该注解的参数对应的类必须存在，否则不解析该注解修饰的配置类；
 * @ConditionalOnMissingBean：该注解表示，如果存在它修饰的类的bean，则不需要再创建这个bean；可以给该注解传入参数 例如@ConditionOnMissingBean(name = "example")，这个表示如果name为“example”的bean存在，这该注解修饰的代码块不执行。
 */
public class ShiroManager {
    /**
     * 保证实现了Shiro内部lifecycle函数的bean执行
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    @ConditionalOnMissingBean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        System.out.println("---------------1----------------");
        return new LifecycleBeanPostProcessor();
    }

    //@Bean(name = "defaultAdvisorAutoProxyCreator")
    //@ConditionalOnMissingBean
    //@DependsOn("lifecycleBeanPostProcessor")
    //public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
    //    DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
    //    defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
    //    return defaultAdvisorAutoProxyCreator;
    //
    //}

    /**
     * 用户授权信息Cache
     */
    @Bean(name = "shiroCacheManager")
    @ConditionalOnMissingBean
    public CacheManager cacheManager() {
        System.out.println("---------------2----------------");
        return new MemoryConstrainedCacheManager();
    }

    @Bean(name = "securityManager")
    @ConditionalOnMissingBean
    public DefaultSecurityManager securityManager(CacheManager shiroCacheManager) {
        System.out.println("---------------DefaultSecurityManager----------------");
        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
        //dwsm.setRealm();
        dwsm.setSessionManager(defaultSessionManager());

        // 关闭session存储
        //((DefaultSessionStorageEvaluator) ((DefaultSubjectDAO) dwsm.getSubjectDAO()).getSessionStorageEvaluator()).setSessionStorageEnabled(false);

//      <!-- 用户授权/认证信息Cache, 采用内存 缓存 -->
        dwsm.setCacheManager(shiroCacheManager);

        SecurityUtils.setSecurityManager(dwsm);
        return dwsm;
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultSessionManager defaultSessionManager() {
        System.out.println("-------------DefaultSessionManager-------------");
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //设置session过期时间为2小时(单位：毫秒)，默认为30分钟
        sessionManager.setGlobalSessionTimeout(2 * 60 * 60 * 1000);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdUrlRewritingEnabled(false);

        //如果开启redis缓存且 shiro.redis=true，则shiro session存到redis里
        //if(redisOpen && shiroRedis){
        //    sessionManager.setSessionDAO(redisShiroSessionDAO);
        //}
        return sessionManager;
    }

    @Bean
    @ConditionalOnMissingBean
    public ShiroFilterRegistry shiroFilterRegistry() {
        return new ShiroFilterRegistry();
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultSecurityManager securityManager) {
        System.out.println("---------------3----------------");
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return new AuthorizationAttributeSourceAdvisor();
    }
}
