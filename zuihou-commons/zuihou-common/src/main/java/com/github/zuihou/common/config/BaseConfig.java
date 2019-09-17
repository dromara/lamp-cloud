package com.github.zuihou.common.config;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.github.zuihou.base.id.CodeGenerate;
import com.github.zuihou.common.converter.EnumDeserializer;
import com.github.zuihou.common.converter.String2DateConverter;
import com.github.zuihou.common.converter.String2LocalDateConverter;
import com.github.zuihou.common.converter.String2LocalDateTimeConverter;
import com.github.zuihou.common.converter.String2LocalTimeConverter;
import com.github.zuihou.common.undertow.UndertowServerFactoryCustomizer;
import com.github.zuihou.utils.SpringUtil;

import io.undertow.Undertow;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import static com.github.zuihou.utils.DateUtils.DEFAULT_DATE_FORMAT;
import static com.github.zuihou.utils.DateUtils.DEFAULT_DATE_TIME_FORMAT;
import static com.github.zuihou.utils.DateUtils.DEFAULT_TIME_FORMAT;

/**
 * 基础配置类
 *
 * @author zuihou
 * @date 2019-06-22 22:53
 */
public abstract class BaseConfig {

    /**
     * 解决序列化和反序列化时，参数转换问题
     * addSerializer：序列化 （Controller 返回 给前端的json）
     * Long -> string
     * BigInteger -> string
     * BigDecimal -> string
     * date -> string
     * <p>
     * addDeserializer: 反序列化 （前端调用接口时，传递到后台的json）
     * 枚举类型: {"code": "xxx"} -> Enum
     *
     * @param builder
     * @return
     */
    @Bean
    @Primary
    @ConditionalOnMissingBean
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        builder.simpleDateFormat(DEFAULT_DATE_TIME_FORMAT);
        ObjectMapper objectMapper = builder.createXmlMapper(false)
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .timeZone(TimeZone.getTimeZone("Asia/Shanghai"))
                .build();

        objectMapper
                .setLocale(Locale.CHINA)
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                //忽略未知字段
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                //该特性决定parser是否允许JSON字符串包含非引号控制字符（值小于32的ASCII字符，包含制表符和换行符）。 如果该属性关闭，则如果遇到这些字符，则会抛出异常。JSON标准说明书要求所有控制符必须使用引号，因此这是一个非标准的特性
                .configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true)
                // 忽略不能转移的字符
                .configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true)
                //单引号处理
                .configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true)
                //日期格式
                .setDateFormat(new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT));

        //反序列化时，属性不存在的兼容处理
        objectMapper.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        SimpleModule simpleModule = new SimpleModule()
                .addSerializer(Long.class, ToStringSerializer.instance)
                .addSerializer(Long.TYPE, ToStringSerializer.instance)
                .addSerializer(BigInteger.class, ToStringSerializer.instance)
                .addSerializer(BigDecimal.class, ToStringSerializer.instance)
                .addDeserializer(Enum.class, EnumDeserializer.INSTANCE);

        return objectMapper.registerModule(simpleModule);
    }

    /**
     * serializerByType 解决json中返回的 LocalDateTime 格式问题
     * deserializerByType 解决string类型入参转为 LocalDateTime 格式问题
     *
     * @return
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> builder
                .serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)))
                .serializerByType(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)))
                .serializerByType(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)))
                .deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)))
                .deserializerByType(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)))
                .deserializerByType(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)));
    }

    /**
     * 解决 @RequestParam(value = "date") Date date
     * date 类型参数 格式问题
     *
     * @return
     */
    @Bean
    public Converter<String, Date> dateConvert() {
        return new String2DateConverter();
    }

    /**
     * 解决 @RequestParam(value = "time") LocalDate time
     *
     * @return
     */
    @Bean
    public Converter<String, LocalDate> localDateConverter() {
        return new String2LocalDateConverter();
    }

    /**
     * 解决 @RequestParam(value = "time") LocalTime time
     *
     * @return
     */
    @Bean
    public Converter<String, LocalTime> localTimeConverter() {
        return new String2LocalTimeConverter();
    }

    /**
     * 解决 @RequestParam(value = "time") LocalDateTime time
     *
     * @return
     */
    @Bean
    public Converter<String, LocalDateTime> localDateTimeConverter() {
        return new String2LocalDateTimeConverter();
    }

    //---------------------------------------序列化配置end----------------------------------------------

    /**
     * 长度都是8位的字符串
     *
     * @param machineCode
     * @return
     */
    @Bean("codeGenerate")
    public CodeGenerate codeGenerate(@Value("${id-generator.machine-code:1}") Long machineCode) {
        return new CodeGenerate(machineCode.intValue());
    }

    /**
     * Spring 工具类
     *
     * @param applicationContext
     * @return
     */
    @Bean
    public SpringUtil springUtil(ApplicationContext applicationContext) {
        SpringUtil.setApplicationContext(applicationContext);
        return new SpringUtil();
    }


    @Bean
    @ConditionalOnClass(Undertow.class)
    public UndertowServerFactoryCustomizer getUndertowServerFactoryCustomizer() {
        return new UndertowServerFactoryCustomizer();
    }

    /////////////////////////////////////////////以下是拦截器配置///////////////////////////////////////////////////////


}
