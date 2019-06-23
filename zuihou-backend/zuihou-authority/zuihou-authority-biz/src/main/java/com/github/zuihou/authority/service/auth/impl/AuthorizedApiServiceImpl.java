package com.github.zuihou.authority.service.auth.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuihou.authority.dao.auth.AuthorizedApiMapper;
import com.github.zuihou.authority.entity.auth.AuthorizedApi;
import com.github.zuihou.authority.service.auth.AuthorizedApiService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * API资源
 * 后端需要授权方可访问的api集合
 * </p>
 *
 * @author zuihou
 * @date 2019-06-23
 */
@Slf4j
@Service
public class AuthorizedApiServiceImpl extends ServiceImpl<AuthorizedApiMapper, AuthorizedApi> implements AuthorizedApiService {

}
