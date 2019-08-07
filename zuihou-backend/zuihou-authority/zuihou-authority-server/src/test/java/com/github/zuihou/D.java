package com.github.zuihou;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
/**
 * 测试DTO
 *
 * @author zuihou
 * @date 2019/07/25
 */
public class D {
    private LocalDateTime date;
    private Date d2;
}
