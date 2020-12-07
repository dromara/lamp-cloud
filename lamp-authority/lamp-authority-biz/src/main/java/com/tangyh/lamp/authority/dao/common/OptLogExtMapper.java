package com.tangyh.lamp.authority.dao.common;

import com.tangyh.basic.base.mapper.SuperMapper;
import com.tangyh.lamp.authority.entity.common.OptLogExt;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

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
     * @param clearBeforeNum  多少条
     * @return 是否成功
     */
    Long clearLog(@Param("clearBeforeTime") LocalDateTime clearBeforeTime, @Param("clearBeforeNum") Integer clearBeforeNum);
}
