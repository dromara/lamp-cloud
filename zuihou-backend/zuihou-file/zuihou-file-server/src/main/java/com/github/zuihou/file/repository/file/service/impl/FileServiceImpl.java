package com.github.zuihou.file.repository.file.service.impl;

import com.github.zuihou.base.dao.BaseDao;
import com.github.zuihou.base.service.impl.BaseServiceImpl;
import com.github.zuihou.file.entity.file.po.ZhFile;
import com.github.zuihou.file.repository.file.dao.ZhFileMapper;
import com.github.zuihou.file.repository.file.example.ZhFileExample;
import com.github.zuihou.file.repository.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zuihou
 * @createTime 2018-01-26 23:05
 */
@Service
public class FileServiceImpl extends BaseServiceImpl
        <Long, ZhFile, ZhFileExample> implements FileService {
    @Autowired
    private ZhFileMapper zhFileMapper;

    @Override
    protected BaseDao<Long, ZhFile, ZhFileExample> getDao() {
        return zhFileMapper;
    }
}
