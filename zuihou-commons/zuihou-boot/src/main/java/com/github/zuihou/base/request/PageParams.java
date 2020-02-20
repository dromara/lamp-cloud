package com.github.zuihou.base.request;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.zuihou.base.entity.Entity;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.utils.AntiSqlFilter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页参数
 *
 * @author zuihou
 * @date 2020年02月14日16:19:36
 */
@Data
@ApiModel(value = "PageParams", description = "分页参数")
public class PageParams<T> {

    @NotNull(message = "查询对象model不能为空")
    @ApiModelProperty(value = "查询参数", required = true)
    private T model;

    @ApiModelProperty(value = "页面大小", example = "10")
    private long size = 10;

    @ApiModelProperty(value = "当前页", example = "1")
    private long current = 1;

    @ApiModelProperty(value = "排序,默认createTime", example = "createTime")
    private String sort = "createTime";

    @ApiModelProperty(value = "排序规则,asc||desc ,默认desc", example = "desc")
    private String order = "desc";

    @ApiModelProperty("扩展参数")
    private Map<String, String> map = new HashMap<>(1);

    @JsonIgnore
    public IPage getPage() {
        PageParams params = this;
        if (StrUtil.isEmpty(params.getSort())) {
            Page page = new Page(params.getCurrent(), params.getSize());
            return page;
        }

        Page page = new Page(params.getCurrent(), params.getSize());
        List<OrderItem> orders = new ArrayList<>();
        // 简单的 驼峰 转 下划线
        String sort = StrUtil.toUnderlineCase(params.getSort());

        // 除了 create_time 和 updateTime 都过滤sql关键字
        if (!StrUtil.equalsAny(params.getSort(), SuperEntity.CREATE_TIME, Entity.UPDATE_TIME)) {
            sort = AntiSqlFilter.getSafeValue(sort);
        }

        orders.add("ascending".equals(params.getOrder()) ? OrderItem.asc(sort) : OrderItem.desc(sort));
        page.setOrders(orders);
        return page;

    }
}
