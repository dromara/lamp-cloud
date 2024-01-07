package top.tangyh.lamp.system.service.system;

import top.tangyh.basic.base.service.SuperService;
import top.tangyh.lamp.system.entity.system.DefDict;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 字典
 * </p>
 *
 * @author zuihou
 * @date 2021-10-04
 */
public interface DefDictService extends SuperService<Long, DefDict> {

    /**
     * 检查字典标识是否可用
     *
     * @param key 标识
     * @param id  当前id
     * @return
     */
    boolean checkByKey(String key, Long id);

    /**
     * 删除字典
     *
     * @param ids
     * @return
     */
    Boolean deleteDict(List<Long> ids);

    /**
     * 复制
     *
     * @param id 字典id
     * @return top.tangyh.lamp.system.entity.system.DefDict
     * @author tangyh
     * @date 2022/4/18 2:39 PM
     * @create [2022/4/18 2:39 PM ] [tangyh] [初始创建]
     */
    @Override
    DefDict copy(Long id);

    /**
     * 根据字典id查询字典项列表
     *
     * @param id 字典ID
     * @return java.util.List<top.tangyh.lamp.tenant.entity.base.DefDict>
     * @author tangyh
     * @date 2021/10/8 11:39 下午
     * @create [2021/10/8 11:39 下午 ] [tangyh] [初始创建]
     */
    List<DefDict> findItemByDictId(Long id);
}
