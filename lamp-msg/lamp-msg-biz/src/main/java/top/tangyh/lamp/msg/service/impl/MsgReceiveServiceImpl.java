package top.tangyh.lamp.msg.service.impl;


import top.tangyh.basic.base.service.SuperServiceImpl;
import top.tangyh.lamp.msg.dao.MsgReceiveMapper;
import top.tangyh.lamp.msg.entity.MsgReceive;
import top.tangyh.lamp.msg.service.MsgReceiveService;
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

public class MsgReceiveServiceImpl extends SuperServiceImpl<MsgReceiveMapper, MsgReceive> implements MsgReceiveService {

}
