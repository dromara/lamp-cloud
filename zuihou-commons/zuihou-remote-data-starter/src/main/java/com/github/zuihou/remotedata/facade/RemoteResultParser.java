package com.github.zuihou.remotedata.facade;

import java.util.List;

/**
 * @author zuihou
 * @create 2020年01月19日09:07:53
 */
public interface RemoteResultParser {
    /**
     * 提取防范返回值中需要合并的有效列表
     *
     * @param methodResult
     * @return
     */
    List parser(Object methodResult);
}
