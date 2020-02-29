package com.github.zuihou.file.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.github.zuihou.database.mybatis.auth.DataScope;
import com.github.zuihou.file.dto.AttachmentResultDTO;
import com.github.zuihou.file.entity.Attachment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     * 按权限查询数据
     *
     * @param page
     * @param wrapper
     * @param dataScope
     * @return
     */
    IPage<Attachment> page(IPage<Attachment> page, @Param(Constants.WRAPPER) Wrapper<Attachment> wrapper, DataScope dataScope);
}
