package com.github.zuihou.gateway.controller;

import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 解决swagger-ui的一个bug
 * <p>
 * 有个前提： nginx的端口要和访问端口一致，否则重定向出错
 *
 * @author tangyh
 * @date 2019-06-21 18:22
 */
@Controller
public class CustomSwaggerController {

    /**
     * 解决基于 网关实现 swagger-bootstrap-ui 的一个bug
     *
     * @param service 服务
     * @param group   组
     * @param ext     uri 后缀
     * @return
     * @throws Exception
     */
    @GetMapping("${server.servlet.context-path}/{service}/v2/{ext}")
    public String apiDocs(@PathVariable String service, @PathVariable String ext, String group) throws Exception {
        String newGroup = group;
        if (group.contains("-")) {
            newGroup = StringUtils.substring(group, group.indexOf("-") + 1);
        }
        return "redirect:/" + service + "/v2/" + ext + "?group=" + URLEncoder.encode(newGroup, "UTF-8");
    }


}
