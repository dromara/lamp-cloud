package top.tangyh.lamp.authority.dao.core;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.tangyh.basic.base.mapper.SuperMapper;
import top.tangyh.lamp.authority.entity.core.Org;

/**
 * <p>
 * Mapper 接口
 * 组织
 * </p>
 *
 * @author zuihou
 * @date 2019-07-22
 */
@Repository
public interface OrgMapper extends SuperMapper<Org> {
    /**
     * 根据 用户ID 查找 部门ID
     *
     * @param userId 用户ID
     * @return
     */
    Org getDeptByUserId(@Param("userId") Long userId);
}
