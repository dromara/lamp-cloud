package com.github.zuihou.poi;

import cn.afterturn.easypoi.handler.inter.IExcelDictHandler;
import com.github.zuihou.authority.enumeration.auth.Sex;
import com.github.zuihou.model.RemoteData;

/**
 * @author by jueyue on 18-8-3.
 */
public class ExcelDictHandlerImpl implements IExcelDictHandler {

    @Override
    public String toName(String dict, Object obj, String name, Object value) {
        if (value == null) {
            return null;
        }
        if ("nation".equals(dict)) {
            return ((RemoteData<String, String>) value).getData();
        }
        return null;
    }

    /**
     * 从名称翻译到值
     *
     * @param dict  字典Key
     * @param obj   对象
     * @param name  属性名称
     * @param value 属性值
     * @return
     */
    @Override
    public String toValue(String dict, Object obj, String name, Object value) {
        if (value == null) {
            return null;
        }
        if ("sex".equals(dict)) {
            return Sex.matchDesc(value.toString(), Sex.N).name();
        }
        if ("education".equals(dict)) {
            return "汉族";
        }
        return null;
    }
}
