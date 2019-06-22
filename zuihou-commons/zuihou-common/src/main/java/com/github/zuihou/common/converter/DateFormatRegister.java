package com.github.zuihou.common.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.cloud.openfeign.FeignFormatterRegistrar;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;

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
    }

    private class Date2StringConverter implements Converter<Date, String> {

        @Override
        public String convert(Date source) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(source);
        }
    }
}
