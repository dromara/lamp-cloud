package com.xxl.job.core.biz.model;

import java.io.Serializable;

/**
 * common return
 *
 * @param <T>
 * @author xuxueli 2015-12-4 16:32:31
 */
public class ReturnT<T> implements Serializable {
    public static final long serialVersionUID = 42L;

    public static final int SUCCESS_CODE = 200;
    public static final int FAIL_CODE = 500;
    public static final int START_JOB_FAI = 101;

    public static final ReturnT<String> SUCCESS = new ReturnT<String>(null);
    public static final ReturnT<String> FAIL = new ReturnT<String>(FAIL_CODE, null);

    private int code;
    private String msg;
    private T content;

    public ReturnT() {
    }

    public ReturnT(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ReturnT(T content) {
        this.code = SUCCESS_CODE;
        this.content = content;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }


    /**
     * 为了 兼容 自己的框架
     *
     * @return
     */
    public Boolean getIsSuccess() {
        if (this.code == 0 || this.code == SUCCESS_CODE) {
            return true;
        }
        return false;
    }

    /**
     * 为了 兼容 自己的框架
     *
     * @return
     */
    public T getData() {
        return this.content;
    }


    @Override
    public String toString() {
        return "ReturnT [code=" + code + ", msg=" + msg + ", content=" + content + "]";
    }

}
