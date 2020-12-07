package com.tangyh.lamp.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.result.view.Rendering;


/**
 * 常用Controller
 *
 * @author zuihou
 * @date 2019-06-21 18:22
 */
@Controller
public class GeneratorController {

    @Value("${server.servlet.context-path:}")
    private String contextPath;

    /**
     * 兼容zuul 文档
     */
    @GetMapping("/gate/doc.html")
    public Rendering doc() {
        String uri = String.format("%s/doc.html", contextPath);
        return Rendering.redirectTo(uri).build();
    }
}
