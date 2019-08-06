package com.github.zuihou.common.constant;

import com.github.zuihou.utils.StrPool;
import com.google.common.base.Joiner;

/**
 * This is a Description
 *
 * @author tangyh
 * @date 2019/08/06
 */
public interface CacheKey {
    String REGISTER_USER = "register";

    /**
     * 构建key
     *
     * @param args
     * @return
     */
    static String build(String... args) {
        return Joiner.on(StrPool.COLON).join(args);
    }
}
