package com.tangyh.lamp.demo.controller.cloud;

import cn.hutool.core.io.IoUtil;
import com.tangyh.basic.base.R;
import com.tangyh.basic.context.ContextUtil;
import com.tangyh.lamp.common.enums.DateType;
import com.tangyh.lamp.example.dto.RestTestDTO;
import com.tangyh.lamp.example.entity.Order;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Map;

/**
 * 1. RestTemplate
 * 2. feignClient 日志
 * 3. feignClient 传递日期参数
 * 4. feignClient 传递文件
 * 5. feignClient 传递请求头和线程变量
 * 6. feignClient & restTemplate 负载均衡
 * 7.
 *
 * @author zuihou
 * @date 2020/6/8 下午7:42
 */
@Slf4j
@RestController
@RequestMapping("/restTemplate")
@Api(value = "restTemplate", tags = "RestTemplate演示")
@RequiredArgsConstructor
public class RestTemplateController {

    private final HttpServletRequest request;
    @Value("${server.port}")
    private Integer port;


    @GetMapping("/get")
    public R<Order> get(@RequestParam("key") String key,
                        @RequestParam(value = "date", required = false) Date date,
                        @RequestParam(value = "ldt", required = false) LocalDateTime ldt) {
        Order order = new Order().setName(key + port);
        order.setCreateTime(ldt).setId(1234L);
        printHeader();
        return R.success(order);
    }

    @PostMapping("/post")
    public R<RestTestDTO> post(@RequestParam("key") String key) {
        RestTestDTO order = new RestTestDTO().setName(key + port);
        order.setLdt(LocalDateTime.now())
                .setBd(new BigDecimal("123.21"))
                .setLd(LocalDate.now())
                .setLt(LocalTime.now())
                .setDate(new Date())
                .setDateType(DateType.DAY)
                .setId(1234L);
        printHeader();
        return R.success(order);
    }

    @PostMapping("/post2")
    public R<RestTestDTO> post2(@RequestBody RestTestDTO order) {
        order.setLd(LocalDate.now())
                .setLt(LocalTime.now())
                .setName("" + port)
                .setDate(new Date());
        printHeader();
        return R.success(order);
    }

    private void printHeader() {
        Map<String, String> localMap = ContextUtil.getLocalMap();
        localMap.forEach((k, v) -> log.info("k={}, v={}", k, v));

        String tenant = request.getHeader("tenant");
        log.info("tenant={}", tenant);

        log.info("port={}", port);
    }

    @SneakyThrows
    @PostMapping(value = "/upload")
    public R<String> upload(
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam(value = "isSingle", required = false, defaultValue = "false") Boolean isSingle,
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "bizId", required = false) String bizId,
            @RequestParam(value = "bizType", required = false) String bizType) {
        printHeader();
        IoUtil.copy(file.getInputStream(), new FileOutputStream("/Users/tangyh/Downloads/uploadFiles/upload" + id + ".jpg"));
        return R.success(file.getOriginalFilename());
    }


    @PostMapping("/fallback")
    public R<RestTestDTO> fallback(@RequestParam("key") String key) {
        RestTestDTO order = new RestTestDTO().setName(key + port);
        order.setLdt(LocalDateTime.now())
                .setBd(new BigDecimal("123.21"))
                .setLd(LocalDate.now())
                .setLt(LocalTime.now())
                .setDate(new Date())
                .setDateType(DateType.DAY)
                .setId(1234L);
        printHeader();

        int a = 1 / 0;
        log.info("a=", a);
        return R.success(order);
    }

    @PostMapping("/fallback2")
    public RestTestDTO fallback2(@RequestParam("key") String key) {
        RestTestDTO order = new RestTestDTO().setName(key + port);
        order.setLdt(LocalDateTime.now())
                .setBd(new BigDecimal("123.21"))
                .setLd(LocalDate.now())
                .setLt(LocalTime.now())
                .setDate(new Date())
                .setDateType(DateType.DAY)
                .setId(1234L);
        printHeader();

        int a = 1 / 0;
        log.info("a=", a);
        return order;
    }

}
