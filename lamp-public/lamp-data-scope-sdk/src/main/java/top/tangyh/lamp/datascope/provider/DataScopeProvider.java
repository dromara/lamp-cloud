package top.tangyh.lamp.datascope.provider;

import top.tangyh.lamp.datascope.model.DataFieldProperty;

import java.util.List;

/**
 * @author zuihou
 * @date 2022/1/9 23:28
 */
public interface DataScopeProvider {
    /**
     * 查询数据字段
     *
     * @param fsp fsp
     * @return java.util.List<top.tangyh.lamp.datascope.model.DataFieldProperty>
     * @author tangyh
     * @date 2022/10/28 4:41 PM
     * @create [2022/10/28 4:41 PM ] [tangyh] [初始创建]
     */
    List<DataFieldProperty> findDataFieldProperty(List<DataFieldProperty> fsp);
}
