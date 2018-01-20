package com.github.zuihou.commons.constant;

/**
 * 删除状态
 *
 * @author zuihou
 * @createTime 2017-12-13 10:08
 */
public enum DeleteStatus {
    DELETE(true, "已删除"),
    UN_DELETE(false, "未删除"),;

    /**  */
    private Boolean val;
    /**  */
    private String describe;

    DeleteStatus(Boolean val, String describe) {
        this.val = val;
        this.describe = describe;
    }

    public Boolean getVal() {
        return val;
    }

    public String getDescribe() {
        return describe;
    }

    public static DeleteStatus parse(Boolean val) {
        if (val == null) {
            return DELETE;
        }
        for (DeleteStatus status : DeleteStatus.values()) {
            if (status.getVal().equals(val)) {
                return status;
            }
        }
        return DELETE;
    }
}
