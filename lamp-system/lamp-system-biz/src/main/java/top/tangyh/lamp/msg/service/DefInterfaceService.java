package top.tangyh.lamp.msg.service;

import top.tangyh.basic.base.service.SuperService;
import top.tangyh.lamp.msg.entity.DefInterface;


/**
 * <p>
 * 业务接口
 * 接口
 * </p>
 *
 * @author zuihou
 * @date 2022-07-04 16:45:45
 * @create [2022-07-04 16:45:45] [zuihou] [代码生成器生成]
 */
public interface DefInterfaceService extends SuperService<Long, DefInterface> {
    /**
     * 检查接口编码是否重复
     *
     * @param code
     * @param id
     * @return
     */
    Boolean check(String code, Long id);
}


