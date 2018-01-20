package com.github.zuihou.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;

/**
 * @author zuihou
 * @createTime 2017-12-16 22:38
 */
public class JSONUtils {
    private static final Logger log = LoggerFactory.getLogger(JSONUtils.class);
    public final static String DEF_SDF = "yyyy-MM-dd HH:mm:ss";

    private JSONUtils() {
    }

    /**
     * 将对象 转换层字符串， 遇到null值，直接输出null, 日期类型转成[yyyy-MM-dd HH:mm:ss]
     *
     * @param object
     * @return
     */
    public static String toJsonString(Object object) {
        return toJsonString(object, false);
    }

    /**
     * 将对象 转换层字符串， 遇到null值，直接忽略转换, 日期类型转成[yyyy-MM-dd HH:mm:ss]
     *
     * @param object
     * @param excludeEmpty
     * @return
     */
    public static String toJsonString(Object object, boolean excludeEmpty) {
        return toJsonString(object, excludeEmpty, DEF_SDF);
    }

    /**
     * 将对象 转换层字符串， 遇到null值，直接忽略转换, 日期类型转成指定格式，默认：[yyyy-MM-dd HH:mm:ss]
     *
     * @param object
     * @param excludeEmpty
     * @param dateSdf
     * @return
     */
    public static String toJsonString(Object object, boolean excludeEmpty, String dateSdf) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            if (excludeEmpty) {
                //如果属性没有值，那么Json是会处理的，int类型为0，String类型为null，数组为[]，设置这个特性可以忽略空值属性
                mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            }
            //日期输出格式
            if (dateSdf == null || dateSdf.isEmpty()) {
                dateSdf = DEF_SDF;
            }
            SimpleDateFormat outputFormat = new SimpleDateFormat(dateSdf);
            mapper.setDateFormat(outputFormat);
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error("Json to string error:", e);
        }
        return null;
    }

    /**
     * 解析字符串成指定类型。 Date 类型的日期 默认为[yyyy-MM-dd HH:mm:ss]
     * <p>
     * 暂时不支持 java8 LocalDateTime的支持，若要需要支持则需要添加jar:
     * <dependency>
     * <groupId>com.fasterxml.jackson.module</groupId>
     * <artifactId>jackson-module-parameter-names</artifactId>
     * </dependency>
     * <dependency>
     * <groupId>com.fasterxml.jackson.datatype</groupId>
     * <artifactId>jackson-datatype-jdk8</artifactId>
     * </dependency>
     * <dependency>
     * <groupId>com.fasterxml.jackson.datatype</groupId>
     * <artifactId>jackson-datatype-jsr310</artifactId>
     * </dependency>
     * 并且在mapper中注册：
     * mapper
     * .registerModule(new ParameterNamesModule())
     * .registerModule(new Jdk8Module())
     * .registerModule(new JavaTimeModule());
     *
     * @param content
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T parse(String content, Class<T> valueType) {
        return parse(content, false, DEF_SDF, valueType);
    }

    /**
     * 解析字符串成指定类型。 Date 类型的日期 默认为[yyyy-MM-dd HH:mm:ss]
     *
     * @param content
     * @param includeEmpty
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T parse(String content, boolean includeEmpty, Class<T> valueType) {
        return parse(content, includeEmpty, DEF_SDF, valueType);
    }

    /**
     * 解析字符串成指定类型。 Date 类型的日期 默认为[yyyy-MM-dd HH:mm:ss]
     * <p>
     * 注意，解析时Date日期格式的必须与生成字符串时指定的日期格式一致，否则解析不出来！！！
     * 如：User类中有Date createTime 字段，生成的json为 {"createTime":"2017-02-11"}
     * 解析时，dateSdf 就必须指定为yyyy-MM-dd HH:mm:ss
     *
     * @param content
     * @param includeEmpty
     * @param dateSdf
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T parse(String content, boolean includeEmpty, String dateSdf, Class<T> valueType) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            if (includeEmpty) {
                //如果属性没有值，那么Json是会处理的，int类型为0，String类型为null，数组为[]，设置这个特性可以忽略空值属性
                mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            }
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            //日期输出格式
            if (dateSdf != null && !dateSdf.isEmpty()) {
                SimpleDateFormat outputFormat = new SimpleDateFormat(dateSdf);
                mapper.setDateFormat(outputFormat);
            }
            return mapper.readValue(content, valueType);
        } catch (Exception e) {
            log.error("Json parse error:", e);
        }
        return null;
    }
}
