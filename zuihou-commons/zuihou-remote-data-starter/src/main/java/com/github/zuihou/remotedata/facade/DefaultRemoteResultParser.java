package com.github.zuihou.remotedata.facade;

import java.util.List;

/**
 * 默认的远程字段查询结果返回值
 *
 * @author zuihou
 * @create 2020年01月19日09:09:56
 */
public class DefaultRemoteResultParser implements RemoteResultParser {
    @Override
    public List parser(Object methodResult) {
        return (List<?>) methodResult;
    }
}
