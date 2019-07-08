package com.github.zuihou.log.event;


import java.util.function.Consumer;

import com.github.zuihou.log.entity.OptLogDTO;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;


/**
 * 异步监听日志事件
 *
 * @author zuihou
 * @date 2019-07-01 15:13
 */
@Slf4j
@AllArgsConstructor
public class SysLogListener {

    private Consumer<OptLogDTO> consumer;

    @Async
    @Order
    @EventListener(SysLogEvent.class)
    public void saveSysLog(SysLogEvent event) {
        log.info("[{}], [{}]", event.getSource(), event.getTimestamp());

        OptLogDTO sysLog = (OptLogDTO) event.getSource();
        consumer.accept(sysLog);
    }

}
