package top.tangyh.lamp.msg.manager;

import top.tangyh.basic.base.manager.SuperManager;
import top.tangyh.lamp.msg.entity.DefInterface;

/**
 * <p>
 * 通用业务接口
 * 接口
 * </p>
 *
 * @author zuihou
 * @date 2022-07-04 16:45:45
 * @create [2022-07-04 16:45:45] [zuihou] [代码生成器生成]
 */
public interface DefInterfaceManager extends SuperManager<DefInterface> {

    /**
     * 根据类型查询接口
     *
     * @param type
     * @return
     */
    DefInterface getByType(String type);
}


