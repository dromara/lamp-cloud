package com.github.zuihou.file.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuihou.file.dao.ShareFileMapper;
import com.github.zuihou.file.dto.ShareFileDTO;
import com.github.zuihou.file.dto.SharePageDTO;
import com.github.zuihou.file.entity.ShareFile;
import com.github.zuihou.file.service.ShareFileService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * 分享文件表
 * </p>
 *
 * @author zuihou
 * @date 2019-06-24
 */
@Slf4j
@Service
public class ShareFileServiceImpl extends ServiceImpl<ShareFileMapper, ShareFile> implements ShareFileService {
    @Override
    public IPage<ShareFileDTO> pageFile(IPage<ShareFileDTO> page, SharePageDTO data) {
        return baseMapper.page(page, data);
    }
}
