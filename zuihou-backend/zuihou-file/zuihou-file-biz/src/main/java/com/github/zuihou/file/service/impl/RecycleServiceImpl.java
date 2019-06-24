package com.github.zuihou.file.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuihou.file.dao.RecycleMapper;
import com.github.zuihou.file.entity.Recycle;
import com.github.zuihou.file.service.RecycleService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * 文件回收站
 * </p>
 *
 * @author zuihou
 * @date 2019-06-24
 */
@Slf4j
@Service
public class RecycleServiceImpl extends ServiceImpl<RecycleMapper, Recycle> implements RecycleService {

}
