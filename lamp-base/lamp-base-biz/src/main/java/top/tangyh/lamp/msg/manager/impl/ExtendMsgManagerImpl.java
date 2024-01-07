package top.tangyh.lamp.msg.manager.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.tangyh.basic.base.manager.impl.SuperManagerImpl;
import top.tangyh.lamp.msg.entity.ExtendMsg;
import top.tangyh.lamp.msg.manager.ExtendMsgManager;
import top.tangyh.lamp.msg.mapper.ExtendMsgMapper;

/**
 * <p>
 * 通用业务实现类
 * 消息
 * </p>
 *
 * @author zuihou
 * @date 2022-07-10 11:41:17
 * @create [2022-07-10 11:41:17] [zuihou] [代码生成器生成]
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ExtendMsgManagerImpl extends SuperManagerImpl<ExtendMsgMapper, ExtendMsg> implements ExtendMsgManager {

}


