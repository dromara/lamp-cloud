package com.github.zuihou.commons.constant;

/**
 * @author zuihou
 * @createTime 2017-12-13 10:08
 */
public enum EnableStatus {

    ENABLE(true, "启用"),
    DISABLE(false, "禁用"),;

    /**  */
    private Boolean val;
    /**  */
    private String describe;

    EnableStatus(Boolean val, String describe) {
        this.val = val;
        this.describe = describe;
    }

    public Boolean getVal() {
        return val;
    }

    public String getDescribe() {
        return describe;
    }

    public static EnableStatus parse(Boolean val) {
        if (val == null) {
            return DISABLE;
        }
        for (EnableStatus status : EnableStatus.values()) {
            if (status.getVal().equals(val)) {
                return status;
            }
        }
        return DISABLE;
    }
}
