package net.oschina.j2cache.cache.support.redis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import net.oschina.j2cache.CacheProviderHolder;
import net.oschina.j2cache.Command;
import net.oschina.j2cache.J2CacheConfig;
import net.oschina.j2cache.cache.support.util.SpringUtil;
import net.oschina.j2cache.cluster.ClusterPolicy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * 使用spring redis实现订阅功能
 *
 * @author zhangsaizz
 */
public class SpringRedisPubSubPolicy implements ClusterPolicy {

    /**
     * 是否是主动模式
     */
    private static boolean isActive = false;
    private int LOCAL_COMMAND_ID = Command.genRandomSrc(); //命令源标识，随机生成，每个节点都有唯一标识
    private RedisTemplate<String, Serializable> redisTemplate;
    private net.oschina.j2cache.autoconfigure.J2CacheConfig config;
    private CacheProviderHolder holder;
    private String channel = "j2cache_channel";

    @Override
    public boolean isLocalCommand(Command cmd) {
        return cmd.getSrc() == LOCAL_COMMAND_ID;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void connect(Properties props, CacheProviderHolder holder) {
        this.holder = holder;
        this.config = SpringUtil.getBean(net.oschina.j2cache.autoconfigure.J2CacheConfig.class);
        if (config.getL2CacheOpen() == false) {
            return;
        }
        J2CacheConfig j2config = SpringUtil.getBean(J2CacheConfig.class);
        this.redisTemplate = SpringUtil.getBean("j2CacheRedisTemplate", RedisTemplate.class);
        String channel_name = j2config.getL2CacheProperties().getProperty("channel");
        if (channel_name != null && !channel_name.isEmpty()) {
            this.channel = channel_name;
        }
        RedisMessageListenerContainer listenerContainer = SpringUtil.getBean("j2CacheRedisMessageListenerContainer", RedisMessageListenerContainer.class);
        String namespace = j2config.getL2CacheProperties().getProperty("namespace");
        String database = j2config.getL2CacheProperties().getProperty("database");
        String expired = "__keyevent@" + (database == null || "".equals(database) ? "0" : database) + "__:expired";
        String del = "__keyevent@" + (database == null || "".equals(database) ? "0" : database) + "__:del";
        List<PatternTopic> topics = new ArrayList<>();
        topics.add(new PatternTopic(expired));
        topics.add(new PatternTopic(del));

        if ("active".equals(config.getCacheCleanMode())) {
            isActive = true;
            //设置键值回调 需要redis支持键值回调
            ConfigureNotifyKeyspaceEventsAction action = new ConfigureNotifyKeyspaceEventsAction();
            action.config(listenerContainer.getConnectionFactory().getConnection());
            listenerContainer.addMessageListener(new SpringRedisActiveMessageListener(this, namespace), topics);
        } else if ("blend".equals(config.getCacheCleanMode())) {
            //设置键值回调 需要redis支持键值回调
            ConfigureNotifyKeyspaceEventsAction action = new ConfigureNotifyKeyspaceEventsAction();
            action.config(listenerContainer.getConnectionFactory().getConnection());
            listenerContainer.addMessageListener(new SpringRedisActiveMessageListener(this, namespace), topics);
            listenerContainer.addMessageListener(new SpringRedisMessageListener(this, this.channel), new PatternTopic(this.channel));
        } else {
            listenerContainer.addMessageListener(new SpringRedisMessageListener(this, this.channel), new PatternTopic(this.channel));
        }

    }

    /**
     * 删除本地某个缓存条目
     *
     * @param region 区域名称
     * @param keys   缓存键值
     */
    public void evict(String region, String... keys) {
        holder.getLevel1Cache(region).evict(keys);
    }

    /**
     * 清除本地整个缓存区域
     *
     * @param region 区域名称
     */
    public void clear(String region) {
        holder.getLevel1Cache(region).clear();
    }

    @Override
    public void publish(Command cmd) {
        if (!isActive && config.getL2CacheOpen()) {
            cmd.setSrc(LOCAL_COMMAND_ID);
            redisTemplate.convertAndSend(this.channel, cmd.json());
        }
    }

    @Override
    public void disconnect() {
        if (!isActive && config.getL2CacheOpen()) {
            Command cmd = new Command();
            cmd.setSrc(LOCAL_COMMAND_ID);
            cmd.setOperator(Command.OPT_QUIT);
            redisTemplate.convertAndSend(this.channel, cmd.json());
        }
    }

}
