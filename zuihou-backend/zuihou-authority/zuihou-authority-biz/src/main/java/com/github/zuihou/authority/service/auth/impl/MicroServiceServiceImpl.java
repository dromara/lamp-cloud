package com.github.zuihou.authority.service.auth.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuihou.authority.dao.auth.MicroServiceMapper;
import com.github.zuihou.authority.entity.auth.MicroService;
import com.github.zuihou.authority.entity.auth.Resource;
import com.github.zuihou.authority.enumeration.auth.ResourceType;
import com.github.zuihou.authority.service.auth.MicroServiceService;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 * 业务实现类
 * 服务表
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
@Slf4j
@Service
public class MicroServiceServiceImpl extends ServiceImpl<MicroServiceMapper, MicroService> implements MicroServiceService {

    @Override
    public void parseUri(List<MicroService> list) {
        if (list.isEmpty()) {
            return;
        }
        list.forEach((ms) -> {
            JSONArray resourceGroups = parseResourceGroups(ms.getSwaggerUrl());
            if (resourceGroups == null || resourceGroups.isEmpty()) {
                return;
            }
            String urlPrefix = StringUtils.substring(ms.getSwaggerUrl(), 0, ms.getSwaggerUrl().lastIndexOf("/swagger-resources"));
            resourceGroups.forEach((resources) -> {
                JSONObject group = (JSONObject) resources;
                String uri = group.getString("url");
                String name = group.getString("name");
                parseGroup(ms, urlPrefix + uri, name);
            });


        });
    }

    private void parseGroup(MicroService ms, String url, String group) {
        //http://127.0.0.1:11006/v2/api-docs?group=priAuth
        JSONObject swagger = parseSwaggerUrl(url);
        if (swagger == null) {
            return;
        }
        JSONObject paths = swagger.getJSONObject("paths");
        if (paths == null) {
            return;
        }
        String basePath = swagger.getString("basePath");

        List<Resource> aaList = new ArrayList<>();
        paths.forEach((path, methodsObj) -> {
            log.info("path={}", path);
            if (methodsObj instanceof JSONObject) {
                JSONObject methods = (JSONObject) methodsObj;

                methods.forEach((method, value) -> {
                    log.info("method={}", method);
                    if (value instanceof JSONObject) {
                        JSONObject val = (JSONObject) value;
                        String summary = StringUtils.substring(val.getString("summary"), 0, 255);
                        String description = StringUtils.substring(val.getString("description"), 0, 255);
                        Boolean deprecated = val.getBoolean("deprecated");
                        JSONObject resource = val.getJSONObject("x-resource");
                        Boolean isAuth = false;
                        if (resource != null) {
                            isAuth = resource.getBoolean("auth");
                        }

                        String code = (basePath + path).replace("/", "_");
                        Resource aa = Resource.builder()
                                .code(code)
                                .resourceType(ResourceType.URI)
                                .name(summary)
                                .menuId(ms.getId())
                                .basePath(basePath)
                                .describe(description)
                                .uri(path)
                                .httpMethod(com.github.zuihou.common.enums.HttpMethod.get(method))
                                .deprecated(deprecated)
                                .isCertification(isAuth)
                                .build();
                        aaList.add(aa);
                    }
                });
            }
        });
        if (!aaList.isEmpty()) {
//            aaList.forEach((aa) -> baseMapper.saveOrUpdateUnique(aa));
        }
    }

    /**
     * 使用RestTemplate获取返回值
     *
     * @param url
     * @return
     */
    private String getSwaggerJsonStr(String url) {
        //复杂构造函数的使用
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        // 设置超时
        requestFactory.setConnectTimeout(1000);
        requestFactory.setReadTimeout(1000);

        RestTemplate client = new RestTemplate(requestFactory);
        HttpHeaders headers = new HttpHeaders();

        //请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        //封装参数，千万不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        //执行HTTP请求
        ResponseEntity<String> response = client.exchange(url, HttpMethod.GET, requestEntity, String.class);
        return response.getBody();
    }

    /**
     * 校验swagger地址的有效性
     *
     * @param swaggerUrl
     * @return
     */
    private JSONObject parseSwaggerUrl(String swaggerUrl) {
        try {
            String jsonStr = getSwaggerJsonStr(swaggerUrl);
            return JSON.parseObject(jsonStr);
        } catch (Exception e) {
            log.info("解析swagger地址错误", e);
        }
        return null;
    }

    private JSONArray parseResourceGroups(String swaggerUrl) {
        try {
            String jsonStr = getSwaggerJsonStr(swaggerUrl);
            return JSON.parseArray(jsonStr);
        } catch (Exception e) {
            log.info("解析swagger地址错误", e);
        }
        return null;
    }

}
