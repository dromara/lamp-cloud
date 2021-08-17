package top.tangyh.lamp.msg.ws;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @author zuihou
 * @date 2021/8/4 23:47
 */
@ServerEndpoint("/anno/myMsg")
@Component
@Slf4j
public class MsgEndpoint {

    /**
     * 连接成功
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        log.info("连接成功");
        WebSocketObserver observer = new WebSocketObserver(session);
        // get subject
        WebSocketSubject subject = WebSocketSubject.Holder.getSubject(session.getId());
        // register observer into subject
        subject.addObserver(observer);
    }

    /**
     * 连接关闭
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        log.info("连接关闭");
        // get subject
        WebSocketSubject subject = WebSocketSubject.Holder.getSubject(session.getId());

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
     * 接收到消息
     *
     * @param text
     */
    @OnMessage
    public String onMsg(String text) {
        if (StrUtil.isEmpty(text)) {
            return "";
        }

        return "servet 发送：" + text;
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.info("连接error");
        throw new RuntimeException("web socket error.", error);
    }
}
