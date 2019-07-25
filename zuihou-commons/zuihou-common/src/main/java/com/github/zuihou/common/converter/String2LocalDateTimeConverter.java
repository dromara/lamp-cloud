package com.github.zuihou.common.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;

import static com.github.zuihou.utils.DateUtils.DEFAULT_DATE_TIME_FORMAT;

/**
 * 解决入参为 Date类型
 *
 * @author zuihou
 * @date 2019-04-30
 */
public class String2LocalDateTimeConverter extends BaseDateConverter<LocalDateTime> implements Converter<String, LocalDateTime> {

    protected static final Map<String, String> FORMAT = new LinkedHashMap(2);

    static {
        FORMAT.put(DEFAULT_DATE_TIME_FORMAT, "^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$");
        FORMAT.put("yyyy/MM/dd HH:mm:ss", "^\\d{4}/\\d{1,2}/\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$");
    }

    @Override
    protected Map<String, String> getFormat() {
        return FORMAT;
    }

    @Override
    public LocalDateTime convert(String source) {
        return super.convert(source, (key) -> LocalDateTime.parse(source, DateTimeFormatter.ofPattern(key)));
    }
}
