package com.github.zuihou.center.context;

import com.github.zuihou.center.configuration.GlobalVariableProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

@Component
@Lazy(false)
public class GlobalVariableContext implements ServletContextAware {

    @Autowired
    private GlobalVariableProperties globalVariableProperties;


    @Override
    public void setServletContext(ServletContext servletContext) {
        String projectName = servletContext.getContextPath();
        if (!"/".equals(projectName)) {
            projectName += "/";
        }
        String staticsPath = projectName + "static";
        String projectNameNotSuffix = projectName.substring(0, projectName.length() - 1);
        servletContext.setAttribute("_gateUrl", globalVariableProperties.getGateWayUrlPrefix());
        servletContext.setAttribute("_baseUrl", projectNameNotSuffix);
        servletContext.setAttribute("_static", staticsPath);
        servletContext.setAttribute("_css", staticsPath + "/css");
        servletContext.setAttribute("_images", staticsPath + "/images");
        servletContext.setAttribute("_js", staticsPath + "/js");
    }
}
