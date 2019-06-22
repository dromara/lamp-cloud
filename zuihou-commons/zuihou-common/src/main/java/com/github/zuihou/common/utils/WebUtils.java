package com.github.zuihou.common.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class WebUtils {

    static String FULL_ROOT_PATH = null;

    public static String getFullRootPath() {
        if (FULL_ROOT_PATH == null) {
            initFullRootPath();
        }
        return FULL_ROOT_PATH;
    }

    private synchronized static void initFullRootPath() {
        if (FULL_ROOT_PATH != null) {
            return;
        }
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        StringBuilder url = new StringBuilder();
        url.append(request.getScheme());
        url.append("://");
        url.append(request.getRemoteHost());
        url.append(":");
        url.append(request.getRemotePort());
        url.append(request.getContextPath());
        FULL_ROOT_PATH = url.toString();
    }

    public static String linkUrl2Params(String url, Object params) {
        if (!url.contains("?")) {
            url += "?";
        } else {
            url += "&";
        }
        return url + params;
    }

    /**
     * public static void main(String[] args) {
     * System.out.println( linkUri2Uri("sdf", "sdf"));
     * System.out.println( linkUri2Uri("sdf/", "sdf"));
     * System.out.println( linkUri2Uri("sdf/", "/sdf"));
     * System.out.println( linkUri2Uri("sdf", "/sdf"));
     * }
     * sdf/sdf
     * sdf/sdf
     * sdf/sdf
     * sdf/sdf
     *
     * @param uriBefore
     * @param uriAfter
     * @return
     */
    public static String linkUri2Uri(String uriBefore, String uriAfter) {
        if (uriBefore.charAt(uriBefore.length() - 1) == '/' || uriAfter.charAt(0) == '/') {
            if (uriBefore.charAt(uriBefore.length() - 1) == '/' && uriAfter.charAt(0) == '/') {
                return uriBefore.substring(0, uriBefore.length() - 1) + uriAfter;
            }
            return uriBefore + uriAfter;
        }
        return uriBefore + '/' + uriAfter;
    }


}
