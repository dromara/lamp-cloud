/*
 * Copyright (c) 2018-2022 Caratacus, (caratacus@qq.com).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.github.zuihou.base;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.exception.BizException;
import com.github.zuihou.exception.code.BaseExceptionCode;
import com.github.zuihou.utils.AntiSqlFilter;
import com.github.zuihou.utils.NumberHelper;

/**
 * SuperController
 *
 * @author Caratacus
 */
public abstract class BaseController {
    @Resource
    protected HttpServletRequest request;
    @Resource
    protected HttpServletResponse response;

    /**
     * 页数
     */
    protected static final String PAGE_NO = "pageNo";
    /**
     * 分页大小
     */
    protected static final String PAGE_SIZE = "pageSize";
    /**
     * 排序字段 ASC
     */
    protected static final String PAGE_ASCS = "ascs";
    /**
     * 排序字段 DESC
     */
    protected static final String PAGE_DESCS = "descs";
    /**
     * 默认每页条目20,最大条目数100
     */
    int DEFAULT_LIMIT = 20;
    int MAX_LIMIT = 10000;

    /**
     * 成功返回
     *
     * @param data
     * @return
     */
    public <T> R<T> success(T data) {
        return R.success(data);
    }

    public R<Boolean> success() {
        return R.success();
    }

    /**
     * 失败返回
     *
     * @param msg
     * @return
     */
    public <T> R<T> fail(String msg) {
        return R.fail(msg);
    }

    /**
     * 失败返回
     *
     * @param code
     * @param msg
     * @return
     */
    public <T> R<T> fail(int code, String msg) {
        return R.fail(code, msg);
    }

    public <T> R<T> fail(BaseExceptionCode exceptionCode) {
        return R.fail(exceptionCode);
    }

    public <T> R<T> fail(BizException exception) {
        return R.fail(exception);
    }

    public <T> R<T> fail(Throwable throwable) {
        return R.fail(throwable);
    }

    public <T> R<T> validFail(String msg) {
        return R.validFail(msg);
    }

    public <T> R<T> validFail(BaseExceptionCode exceptionCode) {
        return R.validFail(exceptionCode);
    }

    /**
     * 获取当前用户id
     */
    protected Long getUserId() {
        return BaseContextHandler.getUserId();
    }

    protected String getAccount() {
        return BaseContextHandler.getAccount();
    }

    protected String getName() {
        return BaseContextHandler.getName();
    }

    /**
     * 获取分页对象
     *
     * @return
     */
    protected <T> Page<T> getPage() {
        return getPage(false);
    }

    protected Integer getPageNo() {
        return NumberHelper.intValueOf(request.getParameter(PAGE_NO), 1);
    }

    protected Integer getPageSize() {
        return NumberHelper.intValueOf(request.getParameter(PAGE_SIZE), DEFAULT_LIMIT);
    }

    /**
     * 获取分页对象
     *
     * @param openSort
     * @return
     */
    protected <T> Page<T> getPage(boolean openSort) {
        // 页数
        Integer pageNo = getPageNo();
        // 分页大小
        Integer pageSize = getPageSize();
        // 是否查询分页
        return buildPage(openSort, pageNo, pageSize);
    }

    private <T> Page<T> buildPage(boolean openSort, long pageNo, long pageSize) {
        // 是否查询分页
        pageSize = pageSize > MAX_LIMIT ? MAX_LIMIT : pageSize;
        Page<T> page = new Page<>(pageNo, pageSize);
        if (openSort) {
            page.setAsc(getParameterSafeValues(PAGE_ASCS));
            page.setDesc(getParameterSafeValues(PAGE_DESCS));
        }
        return page;
    }

    /**
     * 获取安全参数(SQL ORDER BY 过滤)
     *
     * @param parameter
     * @return
     */
    protected String[] getParameterSafeValues(String parameter) {
        return AntiSqlFilter.getSafeValues(request.getParameterValues(parameter));
    }
}
