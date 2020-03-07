package com.github.zuihou.msgs.service.impl;

import com.github.zuihou.base.service.SuperServiceImpl;
import com.github.zuihou.msgs.dao.MsgsCenterInfoReceiveMapper;
import com.github.zuihou.msgs.entity.MsgsCenterInfoReceive;
import com.github.zuihou.msgs.service.MsgsCenterInfoReceiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * 消息中心 接收表
 * 全量数据
 * </p>
 *
 * @author zuihou
 * @date 2019-08-01
 */
@Slf4j
@Service
public class MsgsCenterInfoReceiveServiceImpl extends SuperServiceImpl<MsgsCenterInfoReceiveMapper, MsgsCenterInfoReceive> implements MsgsCenterInfoReceiveService {

}
