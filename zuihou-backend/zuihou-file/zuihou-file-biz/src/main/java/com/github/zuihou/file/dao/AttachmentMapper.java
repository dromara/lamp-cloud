package com.github.zuihou.file.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.zuihou.file.dto.AttachmentResultDTO;
import com.github.zuihou.file.entity.Attachment;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 附件
 * </p>
 *
 * @author zuihou
 * @date 2019-06-24
 */
@Repository
public interface AttachmentMapper extends BaseMapper<Attachment> {
    /**
     * 根据业务类型和业务id， 按照分组查询附件
     *
     * @param bizTypes
     * @param bizIds
     * @return
     */
    List<AttachmentResultDTO> find(@Param("bizTypes") String[] bizTypes, @Param("bizIds") String[] bizIds);

    /**
     * 查询不在指定id集合中的数据
     *
     * @param ids
     * @param group
     * @param path
     * @return
     */
    Integer countByGroup(@Param("ids") List<Long> ids, @Param("group") String group, @Param("path") String path);
}
