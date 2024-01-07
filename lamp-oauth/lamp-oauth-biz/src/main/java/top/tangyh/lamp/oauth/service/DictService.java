package top.tangyh.lamp.oauth.service;

import top.tangyh.basic.interfaces.echo.LoadService;
import top.tangyh.lamp.system.vo.result.system.DefDictItemResultVO;

import java.util.List;
import java.util.Map;

/**
 * 字典查询服务
 *
 * @author zuihou
 * @date 2021/10/7 13:27
 */
public interface DictService extends LoadService {
    /**
     * 根据字典key查询字典条目
     * <p>
     * 1. 先查询租户自己的字典项。
     * 2. 若不存在，则查询系统默认的字典项
     *
     * @param dictKeys 字典key
     * @return key： 字典key  value: item list
     */
    Map<String, List<DefDictItemResultVO>> findDictMapItemListByKey(List<String> dictKeys);
}
