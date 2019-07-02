package com.github.zuihou.common.wrapper;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import lombok.extern.slf4j.Slf4j;

import static com.github.zuihou.common.utils.XssUtils.xssClean;

/**
 * 跨站攻击请求包装器
 *
 * @author zuihou
 * @date 2019-06-28 17:04
 */
@Slf4j
public class XssRequestWrapper extends HttpServletRequestWrapper {

    private List<String> ignoreParamValueList;

    public XssRequestWrapper(HttpServletRequest request, List<String> ignoreParamValueList) {
        super(request);
        this.ignoreParamValueList = ignoreParamValueList;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> request_map = super.getParameterMap();
        Iterator iterator = request_map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry me = (Map.Entry) iterator.next();
            log.info(me.getKey() + ":");
            String[] values = (String[]) me.getValue();
            for (int i = 0; i < values.length; i++) {
                log.info(values[i]);
                values[i] = xssClean(values[i], ignoreParamValueList);
            }
        }
        return request_map;
    }

    @Override
    public String[] getParameterValues(String paramString) {
        String[] arrayOfString1 = super.getParameterValues(paramString);
        if (arrayOfString1 == null) {
            return null;
        }
        int i = arrayOfString1.length;
        String[] arrayOfString2 = new String[i];
        for (int j = 0; j < i; j++) {
            arrayOfString2[j] = xssClean(arrayOfString1[j], ignoreParamValueList);
        }
        return arrayOfString2;
    }

    @Override
    public String getParameter(String paramString) {
        String str = super.getParameter(paramString);
        if (str == null) {
            return null;
        }
        return xssClean(str, ignoreParamValueList);
    }

    @Override
    public String getHeader(String paramString) {
        String str = super.getHeader(paramString);
        if (str == null) {
            return null;
        }
        return xssClean(str, ignoreParamValueList);
    }


}