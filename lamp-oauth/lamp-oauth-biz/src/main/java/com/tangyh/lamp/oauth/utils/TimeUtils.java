package com.tangyh.lamp.oauth.utils;

import cn.hutool.core.convert.Convert;
import com.tangyh.basic.utils.StrPool;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2019/10/25
 */
public class TimeUtils {
    private TimeUtils() {
    }

    public static LocalDateTime getPasswordErrorLockTime(String time) {
        if (time == null || "".equals(time)) {
            return LocalDateTime.MAX;
        }
        if (StrPool.ZERO.equals(time)) {
            return LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        }
        char unit = Character.toLowerCase(time.charAt(time.length() - 1));

        if (time.length() == 1) {
            unit = 'd';
        }
        Long lastTime = Convert.toLong(time.substring(0, time.length() - 1));

        LocalDateTime passwordErrorLastTime;
        switch (unit) {
            //时
            case 'h':
                passwordErrorLastTime = LocalDateTime.now().plusHours(lastTime);
                break;
            //周
            case 'w':
                passwordErrorLastTime = LocalDateTime.now().plusWeeks(lastTime);
                break;
            //月
            case 'm':
                passwordErrorLastTime = LocalDateTime.now().plusMonths(lastTime);
                break;
            //天
            case 'd':
            default:
                passwordErrorLastTime = LocalDateTime.now().plusDays(lastTime);
                break;
        }
        return passwordErrorLastTime;
    }

}


