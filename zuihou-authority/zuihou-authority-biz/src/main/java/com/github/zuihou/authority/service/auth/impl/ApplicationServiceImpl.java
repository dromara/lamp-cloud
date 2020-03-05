package com.github.zuihou.authority.service.auth.impl;

import com.github.zuihou.authority.dao.auth.ApplicationMapper;
import com.github.zuihou.authority.entity.auth.Application;
import com.github.zuihou.authority.service.auth.ApplicationService;
import com.github.zuihou.base.service.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * 应用
 * </p>
 *
 * @author zuihou
 * @date 2019-12-15
 */
@Slf4j
@Service
public class ApplicationServiceImpl extends SuperServiceImpl<ApplicationMapper, Application> implements ApplicationService {

}
