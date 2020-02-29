package com.github.zuihou.file.strategy;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 文件锁工具类
 *
 * @author zuihou
 * @date 2019-06-14
 */
@Component
public class FileLock {

    private static Map<String, Lock> LOCKS = new HashMap<String, Lock>();

    /**
     * 获取锁
     *
     * @param key
     * @return java.util.concurrent.locks.Lock
     * @author zuihou
     * @date 2019-06-14 11:30
     */
    public static synchronized Lock getLock(String key) {
        if (LOCKS.containsKey(key)) {
            return LOCKS.get(key);
        } else {
            Lock one = new ReentrantLock();
            LOCKS.put(key, one);
            return one;
        }
    }

    /**
     * 删除锁
     *
     * @param key
     * @return void
     * @author zuihou
     * @date 2019-06-14 11:33
     */
    public static synchronized void removeLock(String key) {
        LOCKS.remove(key);
    }
}
