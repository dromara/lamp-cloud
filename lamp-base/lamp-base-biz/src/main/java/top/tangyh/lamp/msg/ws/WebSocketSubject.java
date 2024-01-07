package top.tangyh.lamp.msg.ws;


import cn.hutool.core.map.MapUtil;
import top.tangyh.basic.jackson.JsonUtil;
import top.tangyh.basic.utils.ArgumentAssert;

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
    private final String principal;

    private WebSocketSubject(String principal) {
        this.principal = principal;
    }

    public String getPrincipal() {
        return principal;
    }

    /**
     * 通知客户端
     * <p>
     * 如：管理员张三发送消息给李四和王五，ws服务器先推送类型=1的消息给李四和王五，
     * 李四和王五(ws客户端)收到类型为1的消息，主动向服务器拉取消息，
     * ws服务端收到主动拉取消息，会查询数据后，推送消息类型=2的消息给李四和王五，
     * 李四和王五(ws客户端)接收到类型为2的消息，将数据解析后展示在UI页面。
     *
     * @param type 消息类型 1-通知客户端可以拉取数据了 2-通知客户端本次推送数据给你了
     * @param data 数据
     * @author tangyh
     * @date 2021/12/29 5:18 下午
     * @create [2021/12/29 5:18 下午 ] [tangyh] [初始创建]
     */
    public void notify(String type, String data) {
        super.setChanged();

        Map<String, String> map = MapUtil.newHashMap();
        map.put("type", type);
        map.put("data", data);
        super.notifyObservers(JsonUtil.toJson(map));
    }

    public static class Holder {
        private static final Map<String, WebSocketSubject> SUBJECTS = new ConcurrentHashMap<>();

        public static Map<String, WebSocketSubject> getSubject() {
            return SUBJECTS;
        }

        public static WebSocketSubject getSubject(Object principal) {
            ArgumentAssert.notNull(principal, "principal 不能为空");
            if (SUBJECTS.containsKey(principal.toString())) {
                return SUBJECTS.get(principal.toString());
            }

            WebSocketSubject subject = new WebSocketSubject(principal.toString());
            SUBJECTS.put(principal.toString(), subject);
            return subject;
        }
    }

}

