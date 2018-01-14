package com.github.zuihou.admin.ser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author tyh
 * @createTime 2018-01-14 0:05
 */
@Service
@Slf4j
public class Ser {
    public String test(){
        log.error("info---------------");
        log.warn("warn---------------");
        log.info("info---------------");
        log.debug("debug---------------");
        log.trace("trace---------------");
        return "--------";
    }
}
