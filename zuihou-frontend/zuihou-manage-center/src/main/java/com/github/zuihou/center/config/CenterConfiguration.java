package com.github.zuihou.center.config;

import java.util.ArrayList;
import java.util.Collections;

import com.github.zuihou.common.adapter.BaseConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@Primary
public class CenterConfiguration extends BaseConfig {

    @Override
    protected ArrayList<String> getExcludeCommonPathPatterns() {
        ArrayList<String> list = new ArrayList<>();
        String[] urls = {
                "/v2/api-docs",
                "/swagger-resources/**",
                "/client/**",
                "/login",
                "/register",
                "/jwt/**"
        };
        Collections.addAll(list, urls);
        ArrayList<String> superList = super.getExcludeCommonPathPatterns();
        Collections.addAll(list, superList.toArray(new String[superList.size()]));
        return list;
    }


}
