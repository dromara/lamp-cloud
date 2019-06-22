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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.exception.BizException;
import com.github.zuihou.exception.code.BaseExceptionCode;
import com.github.zuihou.utils.AntiSQLFilter;
import com.github.zuihou.utils.NumberHelper;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * SuperController
 *
 * @author Caratacus
 */
public abstract class BaseController {
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;
    /**
     * 页数
     */
    String PAGE_NO = "pageNo";
    /**
     * 分页大小
     */
    String PAGE_SIZE = "pageSize";
    /**
     * 排序字段 ASC
     */
    String PAGE_ASCS = "ascs";
    /**
     * 排序字段 DESC
     */
    String PAGE_DESCS = "descs";
    /**
     * 查询总数
     */
    String SEARCH_COUNT = "searchCount";
    /**
     * 默认每页条目20,最大条目数100
     */
    int DEFAULT_LIMIT = 20;
    int MAX_LIMIT = 100;

    /**
     * 成功返回
     *
     * @param data
     * @return
     */
    public <T> Result<T> success(T data) {
        return Result.success(data);
    }

    /**
     * 失败返回
     *
     * @param msg
     * @return
     */
    public <T> Result<T> fail(String msg) {
        return Result.fail(msg);
    }

    /**
     * 失败返回
     *
     * @param code
     * @param msg
     * @return
     */
    public <T> Result<T> fail(int code, String msg) {
        return Result.fail(code, msg);
    }

    public <T> Result<T> fail(BaseExceptionCode exceptionCode) {
        return Result.fail(exceptionCode);
    }

    public <T> Result<T> fail(BizException exception) {
        return Result.fail(exception);
    }

    public <T> Result<T> fail(Throwable throwable) {
        return Result.fail(throwable);
    }

    public <T> Result<T> validFail(String msg) {
        return Result.validFail(msg);
    }

    public <T> Result<T> validFail(BaseExceptionCode exceptionCode) {
        return Result.validFail(exceptionCode);
    }

    /**
     * 获取当前用户id
     */
    protected Long currentAccountId() {
        return BaseContextHandler.getAccountId();
    }

    protected String currentAccount() {
        return BaseContextHandler.getAccount();
    }

    protected String currentName() {
        return BaseContextHandler.getName();
    }

    protected String currentAppId() {
        return BaseContextHandler.getAppCode();
    }

    protected String currentAppName() {
        return BaseContextHandler.getAppName();
    }

    /**
     * 获取分页对象
     *
     * @return
     */
    protected <T> Page<T> getPage() {
        return getPage(false);
    }

    /**
     * 获取分页对象
     *
     * @param openSort
     * @return
     */
    protected <T> Page<T> getPage(boolean openSort) {
        int index = 1;
        // 页数
        Integer pageNo = NumberHelper.intValueOf(request.getParameter(PAGE_NO), index);
        // 分页大小
        Integer pageSize = NumberHelper.intValueOf(request.getParameter(PAGE_SIZE), DEFAULT_LIMIT);
        // 是否查询分页
        return buildPage(openSort, pageNo, pageSize);
    }

    protected <T> Page<T> getPage(PageParam req) {
        return getPage(req, false);
    }

    protected <T> Page<T> getPage(PageParam req, boolean openSort) {
        // 页数
        long pageNo = req.getPageNo();
        // 分页大小
        long pageSize = req.getPageSize();
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
        return AntiSQLFilter.getSafeValues(request.getParameterValues(parameter));
    }
}
