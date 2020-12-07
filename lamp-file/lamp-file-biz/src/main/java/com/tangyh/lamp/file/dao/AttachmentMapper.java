package com.tangyh.lamp.file.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.tangyh.basic.base.mapper.SuperMapper;
import com.tangyh.basic.database.mybatis.auth.DataScope;
import com.tangyh.lamp.file.dto.AttachmentResultDTO;
import com.tangyh.lamp.file.entity.Attachment;
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
public interface AttachmentMapper extends SuperMapper<Attachment> {
    /**
     * 根据业务类型和业务id， 按照分组查询附件
     *
     * @param bizTypes 业务类型
     * @param bizIds   业务id
     * @return 附件
     */
    List<AttachmentResultDTO> find(@Param("bizTypes") String[] bizTypes, @Param("bizIds") String[] bizIds);

    /**
     * 查询不在指定id集合中的数据
     *
     * @param ids   主键id
     * @param group 分组
     * @param path  路径
     * @return 数量
     */
    Integer countByGroup(@Param("ids") List<Long> ids, @Param("group") String group, @Param("path") String path);

    /**
     * 按权限查询数据
     *
     * @param page      分页参数
     * @param wrapper   条件包装器
     * @param dataScope 数据权限
     * @return 分页数据
     */
    IPage<Attachment> page(IPage<Attachment> page, @Param(Constants.WRAPPER) Wrapper<Attachment> wrapper, DataScope dataScope);
}
