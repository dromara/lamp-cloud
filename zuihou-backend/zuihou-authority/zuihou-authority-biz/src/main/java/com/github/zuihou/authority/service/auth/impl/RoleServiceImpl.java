package com.github.zuihou.authority.service.auth.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuihou.authority.dao.auth.RoleMapper;
import com.github.zuihou.authority.entity.auth.Role;
import com.github.zuihou.authority.service.auth.RoleService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * 角色
 * </p>
 *
 * @author zuihou
 * @date 2019-06-23
 */
@Slf4j
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
