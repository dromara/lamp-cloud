//package com.xxl.job.admin.core.trigger;
//
///**
// * This is a Description
// * 任务类型
// *
// * @author zuihou
// * @date 2018/11/23
// */
//public enum GroupTypeEnum {
//    /**
//     * 表达式定时
//     */
//    AUTO(0, "自动注册"),
//
//    /**
//     * 非表达式-普通指定时间定时
//     */
//    MANUAL(1, "手动注册"),
//    LOCAL(2, "本地执行");
//    private int code;
//    private String title;
//
//    GroupTypeEnum(int code, String title) {
//        this.code = code;
//        this.title = title;
//    }
//
//    public int getCode() {
//        return code;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public boolean eq(Integer type) {
//        if (type != null && type == this.code) {
//            return true;
//        }
//        return false;
//    }
//}
