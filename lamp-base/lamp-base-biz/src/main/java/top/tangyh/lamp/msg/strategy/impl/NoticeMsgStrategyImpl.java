package top.tangyh.lamp.msg.strategy.impl;

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.tangyh.lamp.msg.entity.DefMsgTemplate;
import top.tangyh.lamp.msg.entity.ExtendMsg;
import top.tangyh.lamp.msg.entity.ExtendMsgRecipient;
import top.tangyh.lamp.msg.entity.ExtendNotice;
import top.tangyh.lamp.msg.enumeration.NoticeRemindModeEnum;
import top.tangyh.lamp.msg.manager.ExtendNoticeManager;
import top.tangyh.lamp.msg.strategy.MsgStrategy;
import top.tangyh.lamp.msg.strategy.domain.MsgParam;
import top.tangyh.lamp.msg.strategy.domain.MsgResult;
import top.tangyh.lamp.msg.ws.WebSocketSubject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zuihou
 * @date 2022/7/11 0011 10:29
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeMsgStrategyImpl implements MsgStrategy {
    private final ExtendNoticeManager extendNoticeManager;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public MsgResult exec(MsgParam msgParam) {
        ExtendMsg extendMsg = msgParam.getExtendMsg();
        List<ExtendMsgRecipient> recipientList = msgParam.getRecipientList();
        DefMsgTemplate extendMsgTemplate = msgParam.getExtendMsgTemplate();

        MsgResult msgResult = replaceVariable(extendMsg, extendMsgTemplate);

        List<ExtendNotice> list = new ArrayList<>();
        for (ExtendMsgRecipient extendMsgRecipient : recipientList) {
            ExtendNotice notice = new ExtendNotice();
            BeanUtil.copyProperties(extendMsgTemplate, notice);
            BeanUtil.copyProperties(extendMsg, notice);

            notice.setRecipientId(Long.valueOf(extendMsgRecipient.getRecipient()));
            notice.setTitle(msgResult.getTitle());
            notice.setContent(msgResult.getContent());
            notice.setIsHandle(false);
            notice.setIsRead(false);
            notice.setHandleTime(null);
            notice.setReadTime(null);
            notice.setId(null);
            notice.setRemindMode(NoticeRemindModeEnum.NOTICE.getCode());
            notice.setMsgId(extendMsg.getId());
            list.add(notice);

            WebSocketSubject subject = WebSocketSubject.Holder.getSubject(Long.valueOf(extendMsgRecipient.getRecipient()));
            // 通知客户端 接收消息
            subject.notify("1", null);
        }
        extendNoticeManager.saveBatch(list);
        return msgResult.setResult(true);
    }

    @Override
    public boolean isSuccess(MsgResult result) {
        return (boolean) result.getResult();
    }
}
