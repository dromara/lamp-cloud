package com.github.zuihou.admin.constant;

/**
 * @author zuihou
 * @createTime 2017-12-15 15:30
 */
public enum AppType {
    SYSTEM("内置系统应用"),
    APP("第三方应用"),
    ;
    private String describe;

    AppType(String describe) {
        this.describe = describe;
    }

    public String getDescribe() {
        return describe;
    }

}
