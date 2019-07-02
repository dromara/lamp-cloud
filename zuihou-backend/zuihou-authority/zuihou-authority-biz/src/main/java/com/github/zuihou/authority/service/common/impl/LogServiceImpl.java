package com.github.zuihou.authority.service.common.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuihou.authority.dao.common.LogMapper;
import com.github.zuihou.authority.entity.common.Log;
import com.github.zuihou.authority.service.common.LogService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * 系统日志
 * </p>
 *
 * @author zuihou
 * @date 2019-07-01
 */
@Slf4j
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}
