package com.github.zuihou.base;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.exception.BizException;
import com.github.zuihou.exception.code.BaseExceptionCode;
import com.github.zuihou.utils.AntiSqlFilter;
import com.github.zuihou.utils.NumberHelper;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.github.zuihou.utils.DateUtils.DEFAULT_DATE_TIME_FORMAT;

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
     * 当前页
     */
    protected static final String CURRENT = "current";
    /**
     * 每页显示条数
     */
    protected static final String SIZE = "size";
    /**
     * 排序字段 ASC
     */
    protected static final String PAGE_ASCS = "ascs";
    /**
     * 排序字段 DESC
     */
    protected static final String PAGE_DESCS = "descs";

    protected static final String START_CREATE_TIME = "startCreateTime";
    protected static final String END_CREATE_TIME = "endCreateTime";
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

    public <T> R<T> fail(String msg, Object... args) {
        return R.fail(msg, args);
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

    public <T> R<T> validFail(String msg, Object... args) {
        return R.validFail(msg, args);
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

    protected String getTenant() {
        return BaseContextHandler.getTenant();
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
    protected <T> IPage<T> getPage() {
        return getPage(false);
    }

    protected Integer getCurrent() {
        return NumberHelper.intValueOf(request.getParameter(CURRENT), 1);
    }

    protected Integer getSize() {
        return NumberHelper.intValueOf(request.getParameter(SIZE), DEFAULT_LIMIT);
    }

    /**
     * 获取分页对象
     *
     * @param openSort
     * @return
     */
    protected <T> IPage<T> getPage(boolean openSort) {
        // 页数
        Integer pageNo = getCurrent();
        // 分页大小
        Integer pageSize = getSize();
        // 是否查询分页
        return buildPage(openSort, pageNo, pageSize);
    }

    private <T> Page<T> buildPage(boolean openSort, long current, long size) {
        // 是否查询分页
        size = size > MAX_LIMIT ? MAX_LIMIT : size;
        Page<T> page = new Page<>(current, size);
        if (openSort) {
            page.addOrder(OrderItem.ascs(getParameterSafeValues(PAGE_ASCS)));
            page.addOrder(OrderItem.descs(getParameterSafeValues(PAGE_DESCS)));
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

    protected LocalDateTime getStartCreateTime() {
        return getLocalDateTime(START_CREATE_TIME);
    }

    protected LocalDateTime getEndCreateTime() {
        return getLocalDateTime(END_CREATE_TIME);
    }

    private LocalDateTime getLocalDateTime(String endCreateTime) {
        String startCreateTime = request.getParameter(endCreateTime);
        if (StrUtil.isBlank(startCreateTime)) {
            return null;
        }
        String safeValue = AntiSqlFilter.getSafeValue(startCreateTime);
        if (StrUtil.isBlank(safeValue)) {
            return null;
        }
        return LocalDateTime.parse(safeValue, DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT));
    }
}
