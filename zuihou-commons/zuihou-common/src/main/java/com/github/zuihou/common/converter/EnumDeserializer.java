package com.github.zuihou.common.converter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import lombok.extern.slf4j.Slf4j;

/**
 * enum反序列化工具
 *
 * @author zuihou
 * @date 2019-07-25 22:15
 */
@Slf4j
public class EnumDeserializer extends StdDeserializer<Enum<?>> {
    public final static EnumDeserializer INSTANCE = new EnumDeserializer();
    private final static String ALL_ENUM_STRING_CONVERT_METHOD = "get";
    private final static String ALL_ENUM_KEY_FIELD = "code";

    public EnumDeserializer() {
        super(Enum.class);
    }

    @Override
    public Enum<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonToken token = p.getCurrentToken();
        String value = null;
        while (!token.isStructEnd()) {
            if (ALL_ENUM_KEY_FIELD.equals(p.getText())) {
                p.nextToken();
                value = p.getValueAsString();
            } else {
                p.nextToken();
            }
            token = p.getCurrentToken();
        }

        if (value == null || "".equals(value)) {
            return null;
        }

        Object obj = p.getCurrentValue();
        Class<?> fieldType;
        try {
            //TODO 求优化
            fieldType = obj.getClass().getDeclaredField(p.getCurrentName()).getType();
        } catch (NoSuchFieldException | SecurityException e) {
            try {
                fieldType = obj.getClass().getSuperclass().getDeclaredField(p.getCurrentName()).getType();
            } catch (NoSuchFieldException | SecurityException e2) {
                try {
                    fieldType = obj.getClass().getSuperclass().getSuperclass().getDeclaredField(p.getCurrentName()).getType();
                } catch (NoSuchFieldException | SecurityException e3) {
                    try {
                        fieldType = obj.getClass().getField(p.getCurrentName()).getType();
                    } catch (NoSuchFieldException | SecurityException e1) {
                        log.warn("解析枚举失败", e1);
                        return null;
                    }
                }
            }
        }
        try {
            Method method = fieldType.getMethod(ALL_ENUM_STRING_CONVERT_METHOD, String.class);
            return (Enum<?>) method.invoke(null, value);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {
            log.warn("解析枚举失败", e);
            return null;
        }
    }
}
