package com.github.zuihou.gateway.utils;


import com.github.zuihou.gateway.feign.LogDto;
import com.github.zuihou.gateway.feign.LogService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 日志队列
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-07-01 15:28
 */
@Slf4j
public class DBLog extends Thread {
    private static DBLog dblog = null;
    private static BlockingQueue<LogDto> logInfoQueue = new LinkedBlockingQueue<>(1024);

    public LogService getLogService() {
        return logService;
    }

    public DBLog setLogService(LogService logService) {
        if (this.logService == null) {
            this.logService = logService;
        }
        return this;
    }

    private LogService logService;

    public static synchronized DBLog getInstance() {
        if (dblog == null) {
            dblog = new DBLog();
        }
        return dblog;
    }

    private DBLog() {
        super("CLogOracleWriterThread");
    }

    public void offerQueue(LogDto logInfo) {
        try {
            logInfoQueue.offer(logInfo);
        } catch (Exception e) {
            log.error("日志写入失败", e);
        }
    }

    @Override
    public void run() {
        // 缓冲队列
        List<LogDto> bufferedLogList = new ArrayList<>();
        while (true) {
            try {
                bufferedLogList.add(logInfoQueue.take());
                logInfoQueue.drainTo(bufferedLogList);
                if (!bufferedLogList.isEmpty()) {
                    // 写入日志
                    bufferedLogList.forEach((log) -> logService.saveLog(log));
                }
            } catch (Exception e) {
                log.error("日志保存失败", e);
                // 防止缓冲队列填充数据出现异常时不断刷屏
                try {
                    Thread.sleep(1000);
                } catch (Exception eee) {
                    log.error("Log sleep error:", e);
                }
            } finally {
                if (!bufferedLogList.isEmpty()) {
                    try {
                        bufferedLogList.clear();
                    } catch (Exception e) {
                        log.error("clear bufferedLogList error:", e);
                    }
                }
            }
        }
    }
}