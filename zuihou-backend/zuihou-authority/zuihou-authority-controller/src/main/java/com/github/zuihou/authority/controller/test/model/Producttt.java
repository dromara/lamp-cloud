package com.github.zuihou.authority.controller.test.model;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

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
