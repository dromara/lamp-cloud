package com.github.zuihou.base;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.zuihou.base.entity.SuperEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2019/06/12
 */
@Getter
@Setter
@ApiModel(value = "PageParam", description = "分页对象入参")
public class PageParam<T> implements Serializable {
    /**
     * 默认第一页
     */
    private static int FIRST_PAGE = 1;
    /**
     * 默认页面大小
     */
    private static int DEFAULT_LIMIT = 10;
    /**
     * 最大条目数100
     */
    private static int MAX_LIMIT = 10000;

    /**
     * 第几页   页码从1开始
     */
    @ApiModelProperty(value = "当前页")
    private long pageNo;

    /**
     * 分页大小
     */
    @ApiModelProperty(value = "每页显示数量")
    private long pageSize;

    @NotNull(groups = SuperEntity.OnlyQuery.class, message = "查询对象data不能为空")
    @ApiModelProperty(value = "参数泛型")
    private T data;

    public long getPageNo() {
        if (pageNo <= 0) {
            pageNo = FIRST_PAGE;
        }
        return pageNo;
    }

    public long getPageSize() {
        if (this.pageSize <= 0) {
            pageSize = DEFAULT_LIMIT;
        }
        if (this.pageSize > MAX_LIMIT) {
            pageSize = MAX_LIMIT;
        }
        return pageSize;
    }

    @JsonIgnore
    public <T> Page<T> getPage() {
        return new Page<>(this.getPageNo(), this.getPageSize());
    }

}
