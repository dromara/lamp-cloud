package com.github.zuihou.authority.service.common.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuihou.authority.dao.common.LoginLogMapper;
import com.github.zuihou.authority.entity.common.LoginLog;
import com.github.zuihou.authority.service.common.LoginLogService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * 系统日志
 * </p>
 *
 * @author zuihou
 * @date 2019-10-20
 */
@Slf4j
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

}
