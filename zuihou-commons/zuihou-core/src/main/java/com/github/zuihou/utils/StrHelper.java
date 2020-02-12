package com.github.zuihou.utils;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 字符串帮助类
 *
 * @author zuihou
 * @date 2019-07-16 22:09
 */
@Slf4j

public class StrHelper {
    public static String getOrDef(String val, String def) {
        return StrUtil.isEmpty(val) ? def : val;
    }
}
