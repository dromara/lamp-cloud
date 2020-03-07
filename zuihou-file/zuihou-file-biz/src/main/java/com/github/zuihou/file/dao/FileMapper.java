package com.github.zuihou.file.dao;

import com.github.zuihou.base.mapper.SuperMapper;
import com.github.zuihou.file.domain.FileQueryDO;
import com.github.zuihou.file.domain.FileStatisticsDO;
import com.github.zuihou.file.entity.File;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 文件表
 * </p>
 *
 * @author zuihou
 * @date 2019-06-24
 */
@Repository
public interface FileMapper extends SuperMapper<File> {
    /**
     * 查询文件以及它的父类
     *
     * @param userId
     * @param ids
     * @return
     * @author zuihou
     * @date 2019-05-07 20:49
     */
    List<FileQueryDO> findByIds(@Param("userId") Long userId, @Param("ids") Long[] ids);

    /**
     * 按照日期类型，时间区间，来查询指定用户的各种类型的 数量和大小
     *
     * @param userId
     * @param dateType  日期类型
     * @param dataType  数据类型 数据类型=ALL 按类型统计所有， =指定类型时（不等null）， 统计指定类型 ， =null 时不区分类型统计所有
     * @param startTime
     * @param endTime
     * @return FileStatisticsDO
     */
    List<FileStatisticsDO> findNumAndSizeByUserId(@Param("userId") Long userId,
                                                  @Param("dateType") String dateType,
                                                  @Param("dataType") String dataType,
                                                  @Param("startTime") LocalDateTime startTime,
                                                  @Param("endTime") LocalDateTime endTime);

    /**
     * 查询下次次数前20的文件
     *
     * @param userId
     * @return
     */
    List<FileStatisticsDO> findDownTop20(@Param("userId") Long userId);

    /**
     * 统计时间区间内文件的下次次数和大小
     *
     * @param userId
     * @param dateType  日期类型 {MONTH:按月;WEEK:按周;DAY:按日} 来统计
     * @param startTime
     * @param endTime
     * @return
     */
    List<FileStatisticsDO> findDownSizeByDate(@Param("userId") Long userId,
                                              @Param("dateType") String dateType,
                                              @Param("startTime") LocalDateTime startTime,
                                              @Param("endTime") LocalDateTime endTime);
}
