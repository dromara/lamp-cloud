package net.oschina.j2cache.cache.support.util;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import net.oschina.j2cache.util.SerializationUtils;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;


@Slf4j
public class J2CacheSerializer implements RedisSerializer<Object> {

    @Override
    public byte[] serialize(Object t) throws SerializationException {
        try {
            return SerializationUtils.serialize(t);
        } catch (IOException e) {
            log.warn("J2CacheSerializer serialize error", e);
        }
        return null;
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        try {
            return SerializationUtils.deserialize(bytes);
        } catch (IOException e) {
            log.warn("J2CacheSerializer deserialize error", e);
        }
        return null;
    }

}
