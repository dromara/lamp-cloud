package com.github.zuihou.authority.service.common.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuihou.authority.dao.common.ParameterMapper;
import com.github.zuihou.authority.entity.common.Parameter;
import com.github.zuihou.authority.service.common.ParameterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * 参数配置
 * </p>
 *
 * @author zuihou
 * @date 2020-02-05
 */
@Slf4j
@Service
public class ParameterServiceImpl extends ServiceImpl<ParameterMapper, Parameter> implements ParameterService {

}
