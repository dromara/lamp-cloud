package com.github.zuihou.authority.service.auth.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuihou.authority.dao.auth.AuthorizedApiResourceMapper;
import com.github.zuihou.authority.entity.auth.AuthorizedApiResource;
import com.github.zuihou.authority.service.auth.AuthorizedApiResourceService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * 资源API分配
 * 资源中需要请求的api  并且此api会被鉴权，若不需要鉴权的api就不要加入到
 * </p>
 *
 * @author zuihou
 * @date 2019-06-23
 */
@Slf4j
@Service
public class AuthorizedApiResourceServiceImpl extends ServiceImpl<AuthorizedApiResourceMapper, AuthorizedApiResource> implements AuthorizedApiResourceService {

}
