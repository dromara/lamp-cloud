package com.github.zuihou.log.event;


import com.github.zuihou.authority.entity.common.OptLog;

import org.springframework.context.ApplicationEvent;

/**
 * 系统日志事件
 *
 * @author zuihou
 * @date 2019-07-01 15:13
 */
public class SysLogEvent extends ApplicationEvent {

    public SysLogEvent(OptLog source) {
        super(source);
    }
}
