package top.tangyh.lamp.datascope;

import cn.hutool.core.util.ArrayUtil;
import top.tangyh.lamp.datascope.model.DataFieldProperty;

import java.util.Arrays;
import java.util.List;

/**
 * 数据权限保证类
 *
 * @author tangyh
 * @version v1.0
 * @date 2022/9/16 9:33 PM
 * @create [2022/9/16 9:33 PM ] [tangyh] [初始创建]
 */
public class DataScopeHelper {
    protected static final ThreadLocal<List<DataFieldProperty>> LOCAL_DATA_SCOPE = new ThreadLocal<>();

    /**
     * 获取 数据权限字段配置 参数
     *
     * @return
     */
    public static List<DataFieldProperty> getLocalDataScope() {
        return LOCAL_DATA_SCOPE.get();
    }

    /**
     * 设置 数据权限字段配置
     *
     * @param list
     */
    protected static void setLocalDataScope(List<DataFieldProperty> list) {
        LOCAL_DATA_SCOPE.set(list);
    }

    /**
     * 移除本地变量
     */
    public static void clearDataScope() {
        LOCAL_DATA_SCOPE.remove();
    }

    /**
     * 开启数据权限
     *
     * @param alias sql中需要动态拼接条件的表的别名
     * @author tangyh
     * @date 2022/9/16 9:52 PM
     * @create [2022/9/16 9:52 PM ] [tangyh] [初始创建]
     */
    public static void startDataScope(String... alias) {
        if (ArrayUtil.isEmpty(alias)) {
            return;
        }
        List<DataFieldProperty> list = Arrays.stream(alias).map(DataFieldProperty::new).toList();
        setLocalDataScope(list);
    }
}
