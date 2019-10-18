package net.oschina.j2cache.cache.support.redis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.oschina.j2cache.Level2Cache;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 重新实现二级缓存
 *
 * @author zhangsaizz
 */
public class SpringRedisCache implements Level2Cache {

    private String namespace;

    private String region;

    private RedisTemplate<String, Serializable> redisTemplate;

    public SpringRedisCache(String namespace, String region, RedisTemplate<String, Serializable> redisTemplate) {
        if (region == null || region.isEmpty()) {
            region = "_"; // 缺省region
        }
        this.namespace = namespace;
        this.redisTemplate = redisTemplate;
        this.region = getRegionName(region);
    }

    private String getRegionName(String region) {
        if (namespace != null && !namespace.isEmpty())
            region = namespace + ":" + region;
        return region;
    }

    @Override
    public void clear() {
        redisTemplate.opsForHash().delete(region);
    }

    @Override
    public boolean exists(String key) {
        return redisTemplate.opsForHash().hasKey(region, key);
    }

    @Override
    public void evict(String... keys) {
        for (String k : keys) {
            if (!k.equals("null")) {
                redisTemplate.opsForHash().delete(region, k);
            } else {
                redisTemplate.delete(region);
            }
        }
    }

    @Override
    public Collection<String> keys() {
        Set<Object> list = redisTemplate.opsForHash().keys(region);
        List<String> keys = new ArrayList<>(list.size());
        for (Object object : list) {
            keys.add((String) object);
        }
        return keys;
    }

    @Override
    public byte[] getBytes(String key) {
        return redisTemplate.opsForHash().getOperations().execute((RedisCallback<byte[]>) redis -> redis.hGet(region.getBytes(), key.getBytes()));
    }

    @Override
    public List<byte[]> getBytes(Collection<String> keys) {
        return redisTemplate.opsForHash().getOperations().execute((RedisCallback<List<byte[]>>) redis -> {
            byte[][] bytes = keys.stream().map(k -> k.getBytes()).toArray(byte[][]::new);
            return redis.hMGet(region.getBytes(), bytes);
        });
    }

    @Override
    public void put(String key, Object value) {
        redisTemplate.opsForHash().put(region, key, value);
    }

    /**
     * 设置缓存数据的有效期
     */
    @Override
    public void put(String key, Object value, long timeToLiveInSeconds) {
        redisTemplate.opsForHash().put(region, key, value);
    }

    @Override
    public void setBytes(String key, byte[] bytes) {
        redisTemplate.opsForHash().getOperations().execute((RedisCallback<List<byte[]>>) redis -> {
            redis.set(_key(key).getBytes(), bytes);
            redis.hSet(region.getBytes(), key.getBytes(), bytes);
            return null;
        });
    }

    @Override
    public void setBytes(Map<String, byte[]> bytes) {
        bytes.forEach((k, v) -> {
            setBytes(k, v);
        });
    }

    private String _key(String key) {
        return this.region + ":" + key;
    }

}
