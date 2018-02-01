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

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Configuration
@EnableConfigurationProperties(com.github.shiro.autoconfigure.ShiroProperties.class)
@Import(com.github.shiro.autoconfigure.ShiroManager.class)
public class ShiroAutoConfiguration implements ApplicationContextAware {
    private static Logger log = LoggerFactory.getLogger(ShiroAutoConfiguration.class);

    @Autowired
    private com.github.shiro.autoconfigure.ShiroProperties properties;
    ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        System.out.println("---------------4----------------");
        this.applicationContext = applicationContext;
    }

    @Bean(name = "realm")
    @DependsOn("lifecycleBeanPostProcessor")
    @ConditionalOnMissingBean
    public Realm realm() {
        System.out.println("---------------4----------------");
        Class<?> relmClass = properties.getRealm();
        return (Realm) applicationContext.getBean(relmClass);
    }

    @Bean(name = "shiroFilter")
    @DependsOn("securityManager")
    @ConditionalOnMissingBean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultSecurityManager securityManager, Realm realm,
                                                            com.github.shiro.autoconfigure.ShiroFilterRegistry registry) {
        System.out.println("--------ShiroFilterFactoryBean-----------");
        securityManager.setRealm(realm);

        //Map<String, String> filterDef = swapKeyValue(properties.getFilterChainDefinitions());
        log.info("过虑器配置: {}", properties.getFilterChainDefinitions());
        log.info("自定义过虑器: {}", registry.getFilterMap());

        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        shiroFilter.setLoginUrl(properties.getLoginUrl());
        shiroFilter.setSuccessUrl(properties.getSuccessUrl());
        shiroFilter.setUnauthorizedUrl(properties.getUnauthorizedUrl());

        Map<String, List<String>> filterChainDefinitions = properties.getFilterChainDefinitions();
        shiroFilter.setFilterChainDefinitionMap(swapKeyValue(filterChainDefinitions));
        shiroFilter.getFilters().putAll(registry.getFilterMap());

        return shiroFilter;
    }

    /**
     * 交换Map中的K和V
     *
     * @param originalMap
     * @return
     */
    private <K, V> Map<V, K> swapKeyValue(Map<K, List<V>> originalMap) {
        if (originalMap == null) {
            return null;
        }
        Map<V, K> map = new HashMap<>();
        originalMap.forEach((key, value) -> {
            if (value != null) {
                value.forEach((val) -> {
                    map.put(val, key);
                });
            }
        });
        return map;
    }
}
