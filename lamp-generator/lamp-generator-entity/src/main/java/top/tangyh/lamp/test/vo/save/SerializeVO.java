package top.tangyh.lamp.test.vo.save;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import top.tangyh.basic.annotation.echo.Echo;
import top.tangyh.basic.interfaces.echo.EchoVO;
import top.tangyh.lamp.model.enumeration.Sex;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@ToString
public class SerializeVO implements EchoVO, Serializable {
    private Map<String, Object> echoMap = new HashMap<>();

    /**
     * 支持6种格式
     */
    private LocalDateTime localDateTime;

    private Date date;
    private LocalDate localDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalTime localTime;

    private Long lon;
    private BigInteger bi;
    private BigDecimal bd;

    @Echo(api = Echo.ENUM_API)
    private Sex sex;

}
