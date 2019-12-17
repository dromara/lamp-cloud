package com.github.zuihou.scan.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.github.zuihou.scan.model.SystemApiSaveDTO;
import com.github.zuihou.scan.model.SystemApiScanSaveDTO;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2019/12/16
 */
public class RequestMappingScanUtils {

    public static SystemApiScanSaveDTO scan(String serviceId, RequestMappingHandlerMapping mapping) {
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();

        List<SystemApiSaveDTO> list = new ArrayList<>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            RequestMappingInfo info = m.getKey();
            HandlerMethod method = m.getValue();
//            if (method.getMethod().getDeclaringClass().getAnnotation(RestController.class) == null) {
//                // 只扫描RestController
//                continue;
//            }

            if (method.getMethodAnnotation(ApiIgnore.class) != null) {
                // 忽略的接口不扫描
                continue;
            }

            Set<MediaType> mediaTypeSet = info.getProducesCondition().getProducibleMediaTypes();
            for (MethodParameter params : method.getMethodParameters()) {
                if (params.hasParameterAnnotation(RequestBody.class)) {
                    mediaTypeSet.add(MediaType.APPLICATION_JSON_UTF8);
                    break;
                }
            }
            String contentType = getMediaTypes(mediaTypeSet);
            // 请求类型
            RequestMethodsRequestCondition methodsCondition = info.getMethodsCondition();
            String requestMethod = getMethods(methodsCondition.getMethods());
            // 请求路径
            PatternsRequestCondition p = info.getPatternsCondition();
            String urls = getUrls(p.getPatterns());
            // 类名
            String className = method.getMethod().getDeclaringClass().getName();
            // 方法名
            String methodName = method.getMethod().getName();
            // md5码
            String code = DigestUtils.md5Hex(serviceId + urls);
            String name = "";
            String describe = "";
            // 是否需要安全认证 默认:1-是 0-否
            Boolean isAuth = true;
            // 匹配项目中.permitAll()配置
            for (String url : p.getPatterns()) {
                //TODO 判断接口是否忽略认证
//                for (RequestMatcher requestMatcher : permitAll) {
//                    if (requestMatcher instanceof AntPathRequestMatcher) {
//                        AntPathRequestMatcher pathRequestMatcher = (AntPathRequestMatcher) requestMatcher;
//                        if (pathMatch.match(pathRequestMatcher.getPattern(), url)) {
//                            // 忽略验证
//                            isAuth = "0";
//                        }
//                    }
//                }
            }

            ApiOperation apiOperation = method.getMethodAnnotation(ApiOperation.class);
            if (apiOperation != null) {
                name = apiOperation.value();
                describe = apiOperation.notes();
            }
            name = StrUtil.isBlank(name) ? methodName : name;
            SystemApiSaveDTO api = SystemApiSaveDTO.builder()
                    .name(name).code(code).describe(describe)
                    .requestMethod(requestMethod).contentType(contentType)
                    .serviceId(serviceId).path(urls)
                    .status(true).isPersist(true).isAuth(isAuth)
                    .isOpen(false)
                    .className(className).methodName(methodName)
                    .build();
            list.add(api);
        }
        SystemApiScanSaveDTO scan = SystemApiScanSaveDTO.builder()
                .serviceId(serviceId)
                .systemApiList(list)
                .build();
        return scan;
    }


    private static String getMediaTypes(Set<MediaType> mediaTypes) {
        StringBuilder sbf = new StringBuilder();
        for (MediaType mediaType : mediaTypes) {
            sbf.append(mediaType.toString()).append(",");
        }
        if (mediaTypes.size() > 0) {
            sbf.deleteCharAt(sbf.length() - 1);
        }
        return sbf.toString();
    }

    private static String getMethods(Set<RequestMethod> requestMethods) {
        StringBuilder sbf = new StringBuilder();
        for (RequestMethod requestMethod : requestMethods) {
            sbf.append(requestMethod.toString()).append(",");
        }
        if (requestMethods.size() > 0) {
            sbf.deleteCharAt(sbf.length() - 1);
        }
        return sbf.toString();
    }

    private static String getUrls(Set<String> urls) {
        StringBuilder sbf = new StringBuilder();
        for (String url : urls) {
            sbf.append(url).append(",");
        }
        if (urls.size() > 0) {
            sbf.deleteCharAt(sbf.length() - 1);
        }
        return sbf.toString();
    }
}
