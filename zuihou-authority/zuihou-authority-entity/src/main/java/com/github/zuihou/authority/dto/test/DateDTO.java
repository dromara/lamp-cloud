package com.github.zuihou.authority.dto.test;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.zuihou.authority.entity.core.Org;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 日期测试类 DTO
 *
 * @author zuihou
 * @date 2019/07/24
 */

@Data
@ToString
public class DateDTO implements Serializable {
    private Date date;
    private LocalDateTime dt;
    private LocalDate d;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalTime t;

    public static void main(String[] args) {
        List<Org> list = new ArrayList<>();
        list.add(Org.builder().id(Long.MAX_VALUE).build());
        list.add(Org.builder().id(2L).build());

        String[] ids = list.stream().map((org) -> String.valueOf(org.getId())).toArray(String[]::new);
        for (String id : ids) {
            System.out.println(id);
        }

//        Long[] ids = list.stream().mapToLong(Org::getId).boxed().toArray(Long[]::new);
//        for (Long id : ids) {
//            System.out.println(id);
//        }


    }

}
