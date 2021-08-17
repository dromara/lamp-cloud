package top.tangyh.lamp.msg.ws;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 * Web Socket 观察者.</br>
 *
 * @author zuihou
 * @date 2021/8/5 14:59
 */
public class WebSocketObserver implements Observer {

    /**
     * Web Socket session
     */
    private Session session;

    public WebSocketObserver(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    @Override
    public void update(Observable o, Object arg) {
        String message = (String) arg;
        try {
            if (session.isOpen()) {
                session.getBasicRemote().sendText(message);
            }
        } catch (IOException e) {
            throw new RuntimeException("ws 发送消息失败", e);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((session == null) ? 0 : session.getId().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        WebSocketObserver other = (WebSocketObserver) obj;
        if (session == null) {
            if (other.session != null) {
                return false;
            }
        }
        return session.getId().equals(other.session.getId());
    }

}
