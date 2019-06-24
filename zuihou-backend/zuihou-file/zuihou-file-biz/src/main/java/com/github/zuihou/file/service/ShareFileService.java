package com.github.zuihou.file.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.zuihou.file.dto.ShareFileDTO;
import com.github.zuihou.file.dto.SharePageDTO;
import com.github.zuihou.file.entity.ShareFile;

/**
 * <p>
 * 业务接口
 * 分享文件表
 * </p>
 *
 * @author zuihou
 * @date 2019-06-24
 */
public interface ShareFileService extends IService<ShareFile> {

    /**
     * 查询分享出去的文件内容分页
     *
     * @param page
     * @param data
     * @return
     */
    IPage<ShareFileDTO> pageFile(IPage<ShareFileDTO> page, SharePageDTO data);
}
