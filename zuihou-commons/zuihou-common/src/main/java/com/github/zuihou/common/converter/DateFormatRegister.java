package com.github.zuihou.common.converter;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.cloud.openfeign.FeignFormatterRegistrar;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;

import static com.github.zuihou.common.adapter.BaseConfig.DEFAULT_DATE_TIME_FORMAT;

/**
 * 在feign调用方配置， 解决入参和出参是 date 类型.
 *
 * @author zuihou
 * @return
 */
public class DateFormatRegister implements FeignFormatterRegistrar {

    public DateFormatRegister() {
    }

    @Override
    public void registerFormatters(FormatterRegistry registry) {
        registry.addConverter(Date.class, String.class, new Date2StringConverter());
        registry.addConverter(LocalDateTime.class, String.class, new LocalDateTime2StringConverter());
    }

    private class Date2StringConverter implements Converter<Date, String> {
        @Override
        public String convert(Date source) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(source);
        }
    }

    private class LocalDateTime2StringConverter implements Converter<LocalDateTime, String> {
        @Override
        public String convert(LocalDateTime source) {
            if (source == null) {
                return null;
            }
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT);
            return source.format(dateTimeFormatter);
        }
    }
}
