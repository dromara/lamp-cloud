package top.tangyh.lamp.base.service.user;

import top.tangyh.basic.base.service.SuperService;
import top.tangyh.lamp.base.entity.user.BasePosition;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 业务接口
 * 岗位
 * </p>
 *
 * @author zuihou
 * @date 2021-10-18
 */
public interface BasePositionService extends SuperService<Long, BasePosition> {
    /**
     * 根据id查询待回显参数
     *
     * @param ids 唯一键（可能不是主键ID)
     * @return
     */
    Map<Serializable, Object> findByIds(Set<Serializable> ids);

    /**
     * 检测机构名称是否存在
     *
     * @param name  机构名称
     * @param orgId 上级机构id
     * @param id    岗位id
     * @return
     */
    boolean check(String name, Long orgId, Long id);

}
