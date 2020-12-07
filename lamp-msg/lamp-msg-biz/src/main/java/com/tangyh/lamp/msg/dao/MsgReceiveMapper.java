package com.tangyh.lamp.msg.dao;

import com.tangyh.basic.base.mapper.SuperMapper;
import com.tangyh.lamp.msg.entity.MsgReceive;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 消息中心 接收表
 * 全量数据
 * </p>
 *
 * @author zuihou
 * @date 2019-08-01
 */
@Repository
public interface MsgReceiveMapper extends SuperMapper<MsgReceive> {

}
