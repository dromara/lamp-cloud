package com.github.zuihou.admin.constant;

/**
 * @author zuihou
 * @createTime 2017-12-15 15:40
 */
public enum AdminType  {
    SUPER_ADMIN(0, "超级帐号"),
    GENERAL_ADMIN(1, "普通帐号"),;

    private int val;
    private String describe;

    AdminType(int val, String describe) {
        this.val = val;
        this.describe = describe;
    }

    public int getVal() {
        return val;
    }

    public String getDescribe() {
        return describe;
    }
}
