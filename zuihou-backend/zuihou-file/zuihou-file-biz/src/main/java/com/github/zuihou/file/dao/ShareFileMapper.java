package com.github.zuihou.file.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.file.dto.ShareFileDTO;
import com.github.zuihou.file.dto.SharePageDTO;
import com.github.zuihou.file.entity.ShareFile;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 分享文件表
 * </p>
 *
 * @author zuihou
 * @date 2019-06-24
 */
@Repository
public interface ShareFileMapper extends BaseMapper<ShareFile> {
    /**
     * 自定义分页
     *
     * @param page
     * @param data
     * @return
     * @author tangyh
     * @date 2019-05-08 09:18
     */
    IPage<ShareFileDTO> page(IPage page, @Param("data") SharePageDTO data);
}
