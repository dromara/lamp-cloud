package com.github.zuihou.file.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuihou.file.dao.ShareMapper;
import com.github.zuihou.file.entity.Share;
import com.github.zuihou.file.entity.ShareFile;
import com.github.zuihou.file.service.ShareFileService;
import com.github.zuihou.file.service.ShareService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * 分享目录表
 * </p>
 *
 * @author zuihou
 * @date 2019-06-24
 */
@Slf4j
@Service
public class ShareServiceImpl extends ServiceImpl<ShareMapper, Share> implements ShareService {
    @Autowired
    private ShareFileService shareFileService;

    @Override
    public void cancel(Long userId, Long[] ids) {
        super.remove(Wrappers.<Share>lambdaQuery().eq(Share::getCreateUser, userId).in(Share::getId, ids));
        shareFileService.remove(Wrappers.<ShareFile>lambdaQuery().eq(ShareFile::getAccountId, userId).in(ShareFile::getShareId, ids));
    }
}
