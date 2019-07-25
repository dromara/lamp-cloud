package com.github.zuihou.common.converter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * 所有的恒运enum反序列化工具
 *
 * @author 潘定遥
 */
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
                        e1.printStackTrace();

                        return null;
                    }
                }
            }
        }
        try {

            Method method = fieldType.getMethod(ALL_ENUM_STRING_CONVERT_METHOD, String.class);
            try {
                return (Enum<?>) method.invoke(null, value);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {

                return null;
                //不可能出现此种异常
//                throw new RuntimeException(e);
            }
        } catch (NoSuchMethodException | SecurityException e) {
            //不可能出现此种异常
//            throw new RuntimeException(e);
            return null;
        }
    }
}
