package com.github.zuihou.openfeign.http;

import lombok.extern.slf4j.Slf4j;

/**
 * OkHttp Slf4j logger
 *
 * @author zuihou
 */
@Slf4j
public class OkHttpSlf4jLogger implements HttpLoggingInterceptor.Logger {
    @Override
    public void log(String message) {
        log.info(message);
    }
}
