package com.github.zuihou.gateway.feign;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zuihou
 * @createTime 2017-12-13 15:10
 */
public class LogDto implements Serializable {
    /** 操作菜单 */
    private String menu;
    /** 操作类型[整删改查] */
    private String opt;
    /** 操作uri */
    private String uri;
    /** 操作参数 */
    private String params;

    /** 操作时间 */
    private Date createTime;
    /**  操作人登录名  */
    private String createUser;
    /**  操作人主机ip  */
    private String createHost;

    public LogDto(String menu, String option, String uri, String params, Date createTime, String createUser,  String createHost) {
        this.menu = menu;
        this.opt = option;
        this.uri = uri;
        this.params = params;
        this.createTime = createTime;
        this.createUser = createUser;
        this.createHost = createHost;
    }

    public LogDto() {
    }

}
