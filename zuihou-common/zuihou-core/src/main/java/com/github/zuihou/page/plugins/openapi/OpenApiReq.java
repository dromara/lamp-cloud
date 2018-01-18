package com.github.zuihou.page.plugins.openapi;

import com.github.zuihou.page.AbstractPageRequest;
import com.github.zuihou.page.PageRequest;

import java.io.Serializable;

/**
 * @author zuihou
 * @createTime 2017-12-18 14:53
 */
public class OpenApiReq extends AbstractPageRequest implements PageRequest, Serializable {
    /**
     * 第几页   页码从1开始
     */
    private int pageNo;

    /**
     * 分页大小
     */
    private int pageSize;

    @Override
    public int getPageNo() {
        if (pageNo <= 0) {
            pageNo = FIRST_PAGE;
        }
        return pageNo;
    }

    @Override
    public int getPageSize() {
        if (this.pageSize <= 0) {
            pageSize = DEFAULT_LIMIT;
        }
        return pageSize;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
