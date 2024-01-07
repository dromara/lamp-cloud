package top.tangyh.lamp.msg.strategy.impl;

import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.tangyh.lamp.msg.entity.ExtendMsg;
import top.tangyh.lamp.msg.service.ExtendMsgService;
import top.tangyh.lamp.msg.strategy.MsgStrategy;
import top.tangyh.lamp.msg.strategy.domain.MsgParam;
import top.tangyh.lamp.msg.strategy.domain.MsgResult;

/**
 * @author zuihou
 * @date 2022/7/11 0011 10:29
 */
public class TestMsgStrategyImpl implements MsgStrategy {
    private static final Logger log = LoggerFactory.getLogger(TestMsgStrategyImpl.class);

    @Resource
    private ExtendMsgService extendMsgService;

    @Override
    public MsgResult exec(MsgParam msgParam) {
        System.out.println(" 请开始你的接口逻辑 ");

        ExtendMsg a = extendMsgService.getById(msgParam.getExtendMsg().getId());
        log.info("a {}", a);

        return MsgResult.builder().result("保存成功").build();
    }
}
