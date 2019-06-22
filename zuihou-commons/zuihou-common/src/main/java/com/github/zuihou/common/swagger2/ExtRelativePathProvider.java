package com.github.zuihou.common.swagger2;

import javax.servlet.ServletContext;

import springfox.documentation.spring.web.paths.RelativePathProvider;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2018/11/23
 */
public class ExtRelativePathProvider extends RelativePathProvider {
    private String basePath;

    public ExtRelativePathProvider(ServletContext servletContext, String basePath) {
        super(servletContext);
        this.basePath = basePath;
    }

    @Override
    public String getApplicationBasePath() {
        String applicationPath = super.applicationPath();
        if (ROOT.equals(applicationPath)) {
            applicationPath = "";
        }
        return basePath + applicationPath;
    }
}
