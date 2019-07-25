package com.github.zuihou.authority.dto.test;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2019/07/24
 */

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DateDTO implements Serializable {
    private Date date;
    private LocalDateTime dt;
    private LocalDate d;
    private LocalTime t;
}
