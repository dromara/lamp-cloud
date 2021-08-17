package top.tangyh.lamp.msg.ws;


import cn.hutool.json.JSONObject;

import java.util.Map;
import java.util.Observable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Web Socket 主题(被观察者)
 *
 * @author zuihou
 * @date 2021/8/5 14:59
 */
public class WebSocketSubject extends Observable {

    /**
     * subject键值
     */
    private String principal;

    private WebSocketSubject(String principal) {
        this.principal = principal;
    }

    public String getPrincipal() {
        return principal;
    }

    public void notify(String type, String data) {
        super.setChanged();
        JSONObject json = new JSONObject();
        json.set("type", type);
        json.set("data", data);
        super.notifyObservers(json.toString());
    }

    public static class Holder {
        private static Map<String, WebSocketSubject> subjects = new ConcurrentHashMap<>();

        public static Map<String, WebSocketSubject> getSubject() {
            return subjects;
        }

        public static WebSocketSubject getSubject(String principal) {
            if (subjects.containsKey(principal)) {
                return subjects.get(principal);
            }

            WebSocketSubject subject = new WebSocketSubject(principal);
            subjects.put(principal, subject);
            return subject;
        }
    }

}

