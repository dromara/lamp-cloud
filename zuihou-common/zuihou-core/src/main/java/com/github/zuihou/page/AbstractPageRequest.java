package com.github.zuihou.page;

import java.io.Serializable;

/**
 * @author zuihou
 * @createTime 2017-12-18 14:52
 */
public class AbstractPageRequest implements PageRequest, Serializable {

    @Override
    public int getPageNo() {
        return FIRST_PAGE;
    }

    @Override
    public int getPageSize() {
        return DEFAULT_LIMIT;
    }
}
