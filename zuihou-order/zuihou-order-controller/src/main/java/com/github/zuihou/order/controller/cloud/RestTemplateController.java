package com.github.zuihou.order.controller.cloud;

import com.github.zuihou.base.R;
import com.github.zuihou.common.enums.DateType;
import com.github.zuihou.order.dto.RestTestDTO;
import com.github.zuihou.order.entity.Order;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 1. RestTemplate
 * 6. feignClient & restTemplate 负载均衡
 *
 * @author zuihou
 * @date 2020/6/8 下午7:42
 */
@Slf4j
@RestController
@RequestMapping("/restTemplate")
@Api(value = "restTemplate", tags = "RestTemplate演示")
public class RestTemplateController {

    @Resource(name = "lbRestTemplate")
    RestTemplate restTemplate;
//    @Autowired
//RestTemplate restTemplate;


    @GetMapping("/get")
    public R<Order> get(@RequestParam("key") String key) {
        Map<String, Object> map = new HashMap<>();
        map.put("key", key);
//        map.put("ldt", LocalDateTime.now());
//        map.put("date", DateUtils.formatAsDateTime(new Date()));

        String url = "http://zuihou-demo-server/restTemplate/get?key={key}";
//        String url = "http://zuihou-demo-server/restTemplate/get?key={key}&date={date}&ldt={ldt}";
//        R<Order> r = restTemplate.getForObject(url, R.class, map);

//        HttpEntity httpEntity = new HttpEntity(map);
        ParameterizedTypeReference<R<Order>> ptr = new ParameterizedTypeReference<R<Order>>() {
        };
        ResponseEntity<R<Order>> exchange = restTemplate.exchange(url, HttpMethod.GET, null, ptr, map);

        R<Order> body = exchange.getBody();
        return body;
    }


    @PostMapping("/post")
    public R<RestTestDTO> post(@RequestParam("key") String key) {
        RestTestDTO order = new RestTestDTO().setName(key);
        order.setLdt(LocalDateTime.now())
                .setBd(new BigDecimal(12.23D))
                .setLd(LocalDate.now())
                .setLt(LocalTime.now())
                .setDate(new Date())
                .setDateType(DateType.MONTH)
                .setId(1234L);

        Map<String, String> map = new HashMap<>();
        map.put("key", key + "hahaha");
        String url = "http://zuihou-demo-server/restTemplate/post?key={key}";
        R r = restTemplate.postForObject(url, order, R.class, map);

        ParameterizedTypeReference<R<RestTestDTO>> ptr = new ParameterizedTypeReference<R<RestTestDTO>>() {
        };
        HttpEntity httpEntity = new HttpEntity(order);
        ResponseEntity<R<RestTestDTO>> exchange = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ptr, map);

        R<RestTestDTO> body = exchange.getBody();
        return body;
    }

    @PostMapping("/post2")
    public R<RestTestDTO> post2(@RequestBody RestTestDTO order) {
        String url = "http://zuihou-demo-server/restTemplate/post2";
        R r = restTemplate.postForObject(url, order, R.class);

        ParameterizedTypeReference<R<RestTestDTO>> ptr = new ParameterizedTypeReference<R<RestTestDTO>>() {
        };
        HttpEntity httpEntity = new HttpEntity(order);
        ResponseEntity<R<RestTestDTO>> exchange = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ptr);

        R<RestTestDTO> body = exchange.getBody();
        return body;
    }

}
