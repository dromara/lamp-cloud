package com.github.zuihou.authority.service.defaults.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuihou.authority.dao.defaults.GlobalUserMapper;
import com.github.zuihou.authority.entity.defaults.GlobalUser;
import com.github.zuihou.authority.service.defaults.GlobalUserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * 全局账号
 * </p>
 *
 * @author zuihou
 * @date 2019-10-25
 */
@Slf4j
@Service
public class GlobalUserServiceImpl extends ServiceImpl<GlobalUserMapper, GlobalUser> implements GlobalUserService {

}
