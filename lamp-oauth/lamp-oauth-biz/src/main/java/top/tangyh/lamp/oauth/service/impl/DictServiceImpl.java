package top.tangyh.lamp.oauth.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.tangyh.lamp.oauth.service.DictService;
import top.tangyh.lamp.system.manager.system.DefDictManager;
import top.tangyh.lamp.system.vo.result.system.DefDictItemResultVO;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zuihou
 * @date 2021/10/7 13:27
 */
@Service
@RequiredArgsConstructor
public class DictServiceImpl implements DictService {
    private final DefDictManager defDictManager;

    /**
     * 先从base库查， 若base库没有，在去def库查。
     * 若2个库都有，采用base库的数据
     *
     * @param dictKeys 字典key
     * @return
     */
    @Override
    public Map<String, List<DefDictItemResultVO>> findDictMapItemListByKey(List<String> dictKeys) {
        if (CollUtil.isEmpty(dictKeys)) {
            return Collections.emptyMap();
        }

        // 查询不在base的字典
        Map<String, List<DefDictItemResultVO>> defMap = defDictManager.findDictMapItemListByKey(dictKeys);

        Map<String, List<DefDictItemResultVO>> map = MapUtil.newHashMap();
        map.putAll(defMap);
        return map;
    }


    @Override
    public Map<Serializable, Object> findByIds(Set<Serializable> dictKeys) {
        if (CollUtil.isEmpty(dictKeys)) {
            return Collections.emptyMap();
        }

        Map<Serializable, Object> defMap = defDictManager.findByIds(dictKeys);
        HashMap<Serializable, Object> map = MapUtil.newHashMap();
        map.putAll(defMap);
        return map;
    }
}
