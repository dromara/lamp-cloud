package com.tangyh.lamp.oauth.controller.model;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author tangyh
 * @version v1.0
 * @date 2021/4/28 12:15 上午
 * @create [2021/4/28 12:15 上午 ] [tangyh] [初始创建]
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@Builder
@ApiModel(value = "Option", description = "下拉、多选组件选项")
public class Option {
    private String label;
    private String text;
    private String value;
}
