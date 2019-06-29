package com.github.zuihou.authority.service.auth.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuihou.authority.dao.auth.RoleOrgMapper;
import com.github.zuihou.authority.entity.auth.RoleOrg;
import com.github.zuihou.authority.service.auth.RoleOrgService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * 角色部门关系
 * </p>
 *
 * @author zuihou
 * @date 2019-06-29
 */
@Slf4j
@Service
public class RoleOrgServiceImpl extends ServiceImpl<RoleOrgMapper, RoleOrg> implements RoleOrgService {

}
