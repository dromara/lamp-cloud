package com.github.zuihou.authority.controller.test.model;

import java.io.Serializable;
import java.util.List;

import com.github.zuihou.common.enums.HttpMethod;

import lombok.Data;
import lombok.ToString;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2019/07/30
 */
@Data
@ToString
public class EnumDTO implements Serializable {
    private Long id;
    private String name;
    private HttpMethod httpMethod;

    private List<Producttt> list;
    public void testEx() throws Exception {
        throw new Exception("eeeeee");
    }
}
