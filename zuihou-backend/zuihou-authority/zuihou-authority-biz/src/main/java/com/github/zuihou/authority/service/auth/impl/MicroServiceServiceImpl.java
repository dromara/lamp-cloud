package com.github.zuihou.authority.service.auth.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuihou.authority.dao.auth.MicroServiceMapper;
import com.github.zuihou.authority.entity.auth.MicroService;
import com.github.zuihou.authority.service.auth.MicroServiceService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * 服务表
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
@Slf4j
@Service
public class MicroServiceServiceImpl extends ServiceImpl<MicroServiceMapper, MicroService> implements MicroServiceService {

}
