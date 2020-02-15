package com.github.zuihou.base.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 自定义查询条件
 *
 * @author zuihou
 * @date 2020年02月14日16:23:45
 */
@Data
@EqualsAndHashCode
@ApiModel("扩展参数")
public class RequestParams<T> extends PageParams<T> {

    @ApiModelProperty("扩展参数")
    private Map<String, String> map;

    public RequestParams() {

    }

    public RequestParams(T model) {
        super.setModel(model);
    }


}
