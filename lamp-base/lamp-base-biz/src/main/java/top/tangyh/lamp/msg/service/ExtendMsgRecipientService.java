package top.tangyh.lamp.msg.service;

import top.tangyh.basic.base.service.SuperService;
import top.tangyh.lamp.msg.entity.ExtendMsgRecipient;

import java.util.List;


/**
 * <p>
 * 业务接口
 * 消息接收人
 * </p>
 *
 * @author zuihou
 * @date 2022-07-10 11:41:17
 * @create [2022-07-10 11:41:17] [zuihou] [代码生成器生成]
 */
public interface ExtendMsgRecipientService extends SuperService<Long, ExtendMsgRecipient> {
    /**
     * 根据消息ID查询接收人
     *
     * @param id
     * @return
     */
    List<ExtendMsgRecipient> listByMsgId(Long id);
}


