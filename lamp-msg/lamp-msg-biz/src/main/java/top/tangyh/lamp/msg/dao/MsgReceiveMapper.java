package top.tangyh.lamp.msg.dao;

import org.springframework.stereotype.Repository;
import top.tangyh.basic.base.mapper.SuperMapper;
import top.tangyh.lamp.msg.entity.MsgReceive;

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
