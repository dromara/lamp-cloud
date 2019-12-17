//package com.github.zuihou.authority.service.auth.impl;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.baomidou.mybatisplus.core.toolkit.Wrappers;
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.github.zuihou.authority.dao.auth.MicroServiceMapper;
//import com.github.zuihou.authority.dao.auth.ResourceMapper;
//import com.github.zuihou.authority.entity.auth.MicroService;
//import com.github.zuihou.authority.entity.auth.Resource;
//import com.github.zuihou.authority.service.auth.MicroServiceService;
//import com.github.zuihou.database.mybatis.conditions.Wraps;
//
//import cn.hutool.core.util.StrUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.client.SimpleClientHttpRequestFactory;
//import org.springframework.stereotype.Service;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//
///**
// * <p>
// * 业务实现类
// * 服务表
// * </p>
// *
// * @author zuihou
// * @date 2019-07-03
// */
//@Slf4j
//@Service
//public class MicroServiceServiceImpl extends ServiceImpl<MicroServiceMapper, MicroService> implements MicroServiceService {
//
//
//    @Autowired
//    private DiscoveryClient discovery;
//    @Autowired
//    private ResourceMapper resourceMapper;
//
//    @Override
//    public void sync() {
//        log.info("{}", discovery.getServices().size());
//
//        List<MicroService> list = discovery.getServices()
//                .stream()
//                .map((name) -> discovery.getInstances(name))
//                .filter((li) -> !li.isEmpty()).map((li) -> li.get(0))
//                .map((si) -> {
//                    MicroService ms = super.getOne(Wrappers.<MicroService>lambdaQuery().eq(MicroService::getEurekaCode, si.getServiceId()));
//                    Map<String, String> metadata = si.getMetadata();
//                    String swaggerUrl = "";
//                    String serviceName = "";
//                    if (!metadata.isEmpty()) {
//                        swaggerUrl = metadata.get("swagger");
//                        serviceName = metadata.get("service.name");
//                    }
//                    if (ms == null) {
//                        return MicroService.builder().eurekaCode(si.getServiceId())
//                                .name(serviceName).describe(serviceName).swaggerUrl(swaggerUrl)
//                                .build();
//                    }
//                    ms.setUpdateTime(LocalDateTime.now());
//                    return ms.setName(serviceName).setDescribe(serviceName).setSwaggerUrl(swaggerUrl);
//                })
//                .collect(Collectors.toList());
//
//        if (!list.isEmpty()) {
//            super.saveOrUpdateBatch(list);
//        }
//    }
//
//
//    @Override
//    public void parseUri(Long[] ids) {
//        List<MicroService> list = super.list(Wraps.<MicroService>lbQ().in(MicroService::getId, ids));
//        if (list.isEmpty()) {
//            return;
//        }
//        list.forEach((ms) -> {
//            JSONArray resourceGroups = parseResourceGroups(ms.getSwaggerUrl());
//            if (resourceGroups == null || resourceGroups.isEmpty()) {
//                return;
//            }
//            String urlPrefix = StringUtils.substring(ms.getSwaggerUrl(), 0, ms.getSwaggerUrl().lastIndexOf("/swagger-resources"));
//            resourceGroups.forEach((resources) -> {
//                JSONObject group = (JSONObject) resources;
//                String uri = group.getString("url");
//                String name = group.getString("name");
//                parseGroup(ms, urlPrefix + uri, name);
//            });
//
//        });
//    }
//
//    private void parseGroup(MicroService ms, String url, String group) {
//        log.info("url={}, group={}", url, group);
//        JSONObject swagger = parseSwaggerUrl(url);
//        if (swagger == null) {
//            return;
//        }
//        JSONObject paths = swagger.getJSONObject("paths");
//        if (paths == null) {
//            return;
//        }
//        List<Resource> resourceList = new ArrayList<>();
//        paths.forEach((path, methodsObj) -> {
//            log.info("path={}", path);
//            if (methodsObj instanceof JSONObject) {
//                JSONObject methods = (JSONObject) methodsObj;
//
//                methods.forEach((method, value) -> {
//                    log.info("method={}", method);
//                    if (value instanceof JSONObject) {
//                        JSONObject val = (JSONObject) value;
//                        String summary = StringUtils.substring(val.getString("summary"), 0, 255);
//                        String description = StringUtils.substring(val.getString("description"), 0, 255);
//                        Boolean deprecated = val.getBoolean("deprecated");
//                        JSONArray tagsArray = val.getJSONArray("tags");
//                        String tags = StrUtil.join(",", tagsArray);
//
//                        String code = path.replace("/", "_");
//                        Resource resource = Resource.builder()
//                                .code(code)
////                                .resourceType(ResourceType.URI)
//                                .name(summary)
////                                .microServiceId(ms.getId())
////                                .tags(tags)
//                                .describe(description)
////                                .uri(path)
////                                .httpMethod(com.github.zuihou.common.enums.HttpMethod.get(method))
////                                .deprecated(deprecated)
//                                .build();
//                        resourceList.add(resource);
//                    }
//                });
//            }
//        });
//        if (!resourceList.isEmpty()) {
//            resourceList.forEach((aa) -> resourceMapper.saveOrUpdateUnique(aa));
//        }
//    }
//
//
//    /**
//     * 使用RestTemplate获取返回值
//     *
//     * @param url
//     * @return
//     */
//    private String getSwaggerJsonStr(String url) {
//        //复杂构造函数的使用
//        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
//        // 设置超时
//        requestFactory.setConnectTimeout(1000);
//        requestFactory.setReadTimeout(1000);
//
//        RestTemplate client = new RestTemplate(requestFactory);
//        HttpHeaders headers = new HttpHeaders();
//
//        //请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//        //封装参数，千万不要替换为Map与HashMap，否则参数无法传递
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
//
//        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
//        //执行HTTP请求
//        ResponseEntity<String> response = client.exchange(url, HttpMethod.GET, requestEntity, String.class);
//        return response.getBody();
//    }
//
//    /**
//     * 校验swagger地址的有效性
//     *
//     * @param swaggerUrl
//     * @return
//     */
//    private JSONObject parseSwaggerUrl(String swaggerUrl) {
//        try {
//            String jsonStr = getSwaggerJsonStr(swaggerUrl);
//            return JSON.parseObject(jsonStr);
//        } catch (Exception e) {
//            log.info("解析swagger地址错误", e);
//        }
//        return null;
//    }
//
//    private JSONArray parseResourceGroups(String swaggerUrl) {
//        try {
//            String jsonStr = getSwaggerJsonStr(swaggerUrl);
//            return JSON.parseArray(jsonStr);
//        } catch (Exception e) {
//            log.info("解析swagger地址错误", e);
//        }
//        return null;
//    }
//
//}
