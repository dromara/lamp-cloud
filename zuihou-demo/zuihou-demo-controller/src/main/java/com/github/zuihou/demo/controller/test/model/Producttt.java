package com.github.zuihou.demo.controller.test.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 商品测试DTO
 *
 * @author zuihou
 * @date 2019/08/01
 */
@Data
@ToString
public class Producttt implements Serializable {
    private Long id;
    private String name;
}
