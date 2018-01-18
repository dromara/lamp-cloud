package com.github.zuihou.admin;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zuihou
 * @createTime 2018-01-14 0:00
 */
@Slf4j
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
public class LogTest {
    //@Test
    public void testLog() {
        System.out.println("name=" + log.getName());

        log.error("-------------------------error");
        log.warn("-------------------------warn");
        log.info("-------------------------info");
        log.debug("-------------------------debug");
        log.trace("-------------------------trace");

        System.out.println("----------end---------");
    }
}
