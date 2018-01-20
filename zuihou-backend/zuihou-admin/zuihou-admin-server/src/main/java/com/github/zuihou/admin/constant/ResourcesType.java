package com.github.zuihou.admin.constant;

import java.util.Arrays;
import java.util.List;

/**
 * @author zuihou
 * @createTime 2017-12-18 16:04
 */
public enum ResourcesType {
    DIR("目录"),
    MENU("菜单"),
    BUTTON("按钮"),
    URI("资源"),
    API("接口"),;

    private String describe;

    ResourcesType(String describe) {
        this.describe = describe;
    }

    public String getDescribe() {
        return describe;
    }

    public static ResourcesType[] findResourcesType() {
        return new ResourcesType[]{BUTTON, URI};
    }

    public static List<ResourcesType> listResourcesType() {
        return Arrays.asList(BUTTON, URI);
    }

    public static List<ResourcesType> listMenuType() {
        return Arrays.asList(DIR, MENU);
    }

    public static ResourcesType[] findMenuType() {
        return new ResourcesType[]{DIR, MENU};
    }
}
