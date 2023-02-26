package top.tangyh.lamp.authority.dao.common;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.tangyh.basic.base.mapper.SuperMapper;
import top.tangyh.lamp.authority.entity.common.OptLogExt;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 系统日志
 * </p>
 *
 * @author zuihou
 * @date 2019-07-02
 */
@Repository
public interface OptLogExtMapper extends SuperMapper<OptLogExt> {
    /**
     * 清理日志
     *
     * @param clearBeforeTime 多久之前的
     * @param idList  待删除
     * @return 是否成功
     */
    Long clearLog(@Param("clearBeforeTime") LocalDateTime clearBeforeTime, @Param("idList") List<Long> idList);
}
