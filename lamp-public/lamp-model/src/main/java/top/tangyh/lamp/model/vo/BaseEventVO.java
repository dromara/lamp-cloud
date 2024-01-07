package top.tangyh.lamp.model.vo;

import cn.hutool.core.collection.CollUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import top.tangyh.basic.context.ContextUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tangyh
 * @version v1.0
 * @date 2022/7/29 10:04 PM
 * @create [2022/7/29 10:04 PM ] [tangyh] [初始创建]
 */
@Data
@Accessors(chain = true)
public class BaseEventVO {
    private Map<String, String> map;

    /**
     * 将线程变量副 暂存到map
     * 适用于：异步调用前
     *
     * @return top.tangyh.lamp.model.vo.BaseEventVO
     * @author tangyh
     * @date 2022/7/29 11:12 PM
     * @create [2022/7/29 11:12 PM ] [tangyh] [初始创建]
     */
    public BaseEventVO copy() {
        if (map == null) {
            map = new HashMap<>();
        }
        map.clear();
        map.putAll(ContextUtil.getLocalMap());
        return this;
    }

    /**
     * 将map写入线程变量
     * 适用于：异步执行一开始
     *
     * @return top.tangyh.lamp.model.vo.BaseEventVO
     * @author tangyh
     * @date 2022/7/29 11:12 PM
     * @create [2022/7/29 11:12 PM ] [tangyh] [初始创建]
     */
    public BaseEventVO write() {
        if (CollUtil.isNotEmpty(map)) {
            ContextUtil.setLocalMap(map);
        }
        return this;
    }

}
