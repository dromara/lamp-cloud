package top.tangyh.lamp.test.vo.save;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Data
@ToString
public class DateVO implements Serializable {
    private Date date;
    private LocalDateTime localDateTime;
    private LocalDate localDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalTime localTime;
    private LocalTime localTime2;
}
