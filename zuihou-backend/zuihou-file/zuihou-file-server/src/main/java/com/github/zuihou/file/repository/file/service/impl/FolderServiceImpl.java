package com.github.zuihou.file.repository.file.service.impl;

import com.github.zuihou.base.dao.BaseDao;
import com.github.zuihou.base.service.impl.BaseServiceImpl;
import com.github.zuihou.file.entity.file.po.ZhFolder;
import com.github.zuihou.file.repository.file.dao.ZhFolderMapper;
import com.github.zuihou.file.repository.file.example.ZhFolderExample;
import com.github.zuihou.file.repository.file.service.FolderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zuihou
 * @createTime 2018-01-26 23:05
 */
@Service
@Slf4j
public class FolderServiceImpl extends BaseServiceImpl<Long,
        ZhFolder, ZhFolderExample> implements FolderService {
    @Autowired
    private ZhFolderMapper zhFolderMapper;

    @Override
    protected BaseDao<Long, ZhFolder, ZhFolderExample> getDao() {
        return zhFolderMapper;
    }
}

