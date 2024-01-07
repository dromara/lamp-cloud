package top.tangyh.lamp.oauth.service;

import top.tangyh.basic.interfaces.echo.LoadService;

import java.util.List;
import java.util.Map;

/**
 * 字典查询服务
 *
 * @author zuihou
 * @date 2021/10/7 13:27
 */
public interface ParamService extends LoadService {
    /**
     * 根据参数key查参数值
     * <p>
     * 1. 先查询租户自己的参数。
     * 2. 若不存在，则查询系统默认的参数。
     *
     * @param paramsKeys 参数key
     * @return key： 参数key  value: 参数值
     */
    Map<String, String> findParamMapByKey(List<String> paramsKeys);
}
