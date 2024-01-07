package top.tangyh.lamp.msg.ws;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.tangyh.basic.base.request.PageParams;
import top.tangyh.basic.context.ContextUtil;
import top.tangyh.basic.database.mybatis.conditions.Wraps;
import top.tangyh.basic.jackson.JsonUtil;
import top.tangyh.basic.utils.BeanPlusUtil;
import top.tangyh.basic.utils.SpringUtils;
import top.tangyh.basic.utils.StrPool;
import top.tangyh.lamp.msg.entity.ExtendNotice;
import top.tangyh.lamp.msg.enumeration.NoticeRemindModeEnum;
import top.tangyh.lamp.msg.service.ExtendNoticeService;
import top.tangyh.lamp.msg.vo.MyMsgResult;
import top.tangyh.lamp.msg.vo.result.ExtendNoticeResultVO;

import java.io.IOException;
import java.util.Map;

/**
 * @author zuihou
 * @date 2021/8/4 23:47
 */
@ServerEndpoint("/anno/myMsg/{tenantId}/{principal}")
@Component
@Slf4j
public class MsgEndpoint {


    /**
     * 连接成功
     *
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("principal") String principal, Session session) {
        log.info("连接成功");
        WebSocketObserver observer = new WebSocketObserver(session);
        // get subject
        WebSocketSubject subject = WebSocketSubject.Holder.getSubject(principal);
        // register observer into subject
        subject.addObserver(observer);
    }

    /**
     * 连接关闭
     *
     * @param session
     */
    @OnClose
    public void onClose(@PathParam("principal") String principal, Session session) {
        log.info("连接关闭");
        // get subject
        WebSocketSubject subject = WebSocketSubject.Holder.getSubject(principal);

        // get observer
        WebSocketObserver observer = new WebSocketObserver(session);
        // delete observer from subject
        subject.deleteObserver(observer);

        // close session and close Web Socket connection
        try {
            if (session.isOpen()) {
                session.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("close web socket session error.", e);
        }
    }

    /**
     * 接收客户端发送的消息， 并返回数据给客户端
     *
     * @param text
     */
    @OnMessage
    public String onMsg(@PathParam("tenantId") String tenantId, @PathParam("principal") String principal, String text) {
        if (StrUtil.isEmpty(text) || "ping".equals(text)) {
            return StrPool.EMPTY;
        }
        log.info("tenantId={}, employeeId={}, text={}", tenantId, principal, text);
        ContextUtil.setEmployeeId(principal);

        PageParams<ExtendNotice> params = new PageParams<>(1, 10);
        ExtendNoticeService superService = SpringUtils.getBean(ExtendNoticeService.class);

        IPage<ExtendNotice> todoList = params.buildPage(ExtendNotice.class);
        IPage<ExtendNotice> noticeList = params.buildPage(ExtendNotice.class);
        IPage<ExtendNotice> earlyWarningList = params.buildPage(ExtendNotice.class);
        superService.page(todoList, Wraps.<ExtendNotice>lbQ()
                .eq(ExtendNotice::getRemindMode, NoticeRemindModeEnum.TO_DO.getValue()).
                eq(ExtendNotice::getIsRead, false).eq(ExtendNotice::getRecipientId, ContextUtil.getEmployeeId()));
        superService.page(noticeList, Wraps.<ExtendNotice>lbQ()
                .eq(ExtendNotice::getRemindMode, NoticeRemindModeEnum.NOTICE.getValue()).
                eq(ExtendNotice::getIsRead, false).eq(ExtendNotice::getRecipientId, ContextUtil.getEmployeeId()));
        superService.page(earlyWarningList, Wraps.<ExtendNotice>lbQ()
                .eq(ExtendNotice::getRemindMode, NoticeRemindModeEnum.EARLY_WARNING.getValue()).
                eq(ExtendNotice::getIsRead, false).eq(ExtendNotice::getRecipientId, ContextUtil.getEmployeeId()));

        MyMsgResult result = MyMsgResult.builder()
                .todoList(BeanPlusUtil.toBeanPage(todoList, ExtendNoticeResultVO.class))
                .noticeList(BeanPlusUtil.toBeanPage(noticeList, ExtendNoticeResultVO.class))
                .earlyWarningList(BeanPlusUtil.toBeanPage(earlyWarningList, ExtendNoticeResultVO.class))
                .build();

        Map<String, Object> map = MapUtil.newHashMap();
        map.put("type", "2");
        map.put("data", result);
        return JsonUtil.toJson(map);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.info("连接error");
        throw new RuntimeException("web socket error.", error);
    }
}
