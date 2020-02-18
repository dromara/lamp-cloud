package com.github.zuihou.base;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.ExcelXorHtmlUtil;
import cn.afterturn.easypoi.excel.entity.ExcelToHtmlParams;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.view.PoiBaseView;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.zuihou.base.request.PageParams;
import com.github.zuihou.base.request.RequestParams;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.exception.BizException;
import com.github.zuihou.exception.code.BaseExceptionCode;
import com.github.zuihou.utils.DateUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SuperController
 *
 * @author Caratacus
 */
public abstract class BaseController2<S extends IService<Entity>, Id extends Serializable, Entity, PageDTO, SaveDTO, UpdateDTO> {

    protected Class<Entity> entityClass = null;
    @Autowired
    protected S baseService;

    protected BaseController2() {
        entityClass = (Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2];
    }

    protected static IPage getPage(PageParams params) {
        return params.getPage();
    }

    protected Class<Entity> getEntityClass() {
        return entityClass;
    }


    /**
     * 成功返回
     *
     * @param data
     * @return
     */
    protected <T> R<T> success(T data) {
        return R.success(data);
    }

    protected R<Boolean> success() {
        return R.success();
    }

    /**
     * 失败返回
     *
     * @param msg
     * @return
     */
    protected <T> R<T> fail(String msg) {
        return R.fail(msg);
    }

    protected <T> R<T> fail(String msg, Object... args) {
        return R.fail(msg, args);
    }

    /**
     * 失败返回
     *
     * @param code
     * @param msg
     * @return
     */
    protected <T> R<T> fail(int code, String msg) {
        return R.fail(code, msg);
    }

    protected <T> R<T> fail(BaseExceptionCode exceptionCode) {
        return R.fail(exceptionCode);
    }

    protected <T> R<T> fail(BizException exception) {
        return R.fail(exception);
    }

    protected <T> R<T> fail(Throwable throwable) {
        return R.fail(throwable);
    }

    protected <T> R<T> validFail(String msg) {
        return R.validFail(msg);
    }

    protected <T> R<T> validFail(String msg, Object... args) {
        return R.validFail(msg, args);
    }

    protected <T> R<T> validFail(BaseExceptionCode exceptionCode) {
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
     * 根据 bean字段 反射出 数据库字段
     *
     * @param beanField
     * @param clazz
     * @return
     */
    protected String getDbField(String beanField, Class<?> clazz) {
        Field field = ReflectUtil.getField(clazz, beanField);
        TableField tf = field.getAnnotation(TableField.class);
        if (tf != null && StringUtils.isNotEmpty(tf.value())) {
            String str = tf.value();
            return str;
        }
        return beanField;
    }

    /**
     * 计算开始时间
     *
     * @param time
     * @return
     */
    protected String getStartTime(String time) {
        if (time.matches("^\\d{4}-\\d{1,2}$")) {
            return time + "-01 00:00:00";
        } else if (time.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
            return time + " 00:00:00";
        } else if (time.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")) {
            return time + ":00";
        } else if (time.matches("^\\d{4}-\\d{1,2}-\\d{1,2}T{1}\\d{1,2}:\\d{1,2}:\\d{1,2}.\\d{3}Z$")) {
            String str = time.replace("T", " ").substring(0, time.indexOf("."));
            return str;
        } else {
            return time;
        }
    }

    /**
     * 计算结束时间
     *
     * @param time
     * @return
     */
    protected String getEndTime(String time) {
        if (time.matches("^\\d{4}-\\d{1,2}$")) {
            Date date = DateUtils.parse(time, "yyyy-MM");
            date = DateUtils.getLastDateOfMonth(date);
            return DateUtils.formatAsDate(date) + " 23:59:59";
        } else if (time.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
            return time + " 23:59:59";
        } else if (time.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")) {
            return time + ":59";
        } else if (time.matches("^\\d{4}-\\d{1,2}-\\d{1,2}T{1}\\d{1,2}:\\d{1,2}:\\d{1,2}.\\d{3}Z$")) {
            time = time.replace("T", " ").substring(0, time.indexOf("."));
            if (time.endsWith("00:00:00")) {
                time = time.replace("00:00:00", "23:59:59");
            }
            return time;
        } else {
            return time;
        }
    }


    protected void handlerWrapper(QueryWrapper wrapper, Map<String, String> map, RequestParams<PageDTO> params) {
        if (CollUtil.isNotEmpty(params.getMap())) {
            //拼装区间
            for (Map.Entry<String, String> field : map.entrySet()) {
                String key = field.getKey();
                String value = field.getValue();
                if (StrUtil.isEmpty(value)) {
                    continue;
                }
                if (key.endsWith("_st")) {
                    String beanField = StrUtil.subBefore(key, "_st", true);
                    wrapper.ge(getDbField(beanField, getEntityClass()), getStartTime(value));
                }
                if (key.endsWith("_ed")) {
                    String beanField = StrUtil.subBefore(key, "_ed", true);
                    wrapper.le(getDbField(beanField, getEntityClass()), getEndTime(value));
                }
            }
        }

        handlerQuery(wrapper, params);
    }

    /**
     * 客户自定义处理查询参数组装
     *
     * @param wrapper
     * @param params
     */
    protected void handlerQuery(QueryWrapper wrapper, RequestParams<PageDTO> params) {

    }

    @ApiOperation(value = "查询分页列表")
    @PostMapping(value = "/page")
    public R<IPage<Entity>> page(@RequestBody @Validated RequestParams<PageDTO> params) {
        // 处理参数
        IPage<Entity> page = params.getPage();
        Entity model = BeanUtil.toBean(params.getModel(), getEntityClass());
        QueryWrapper<Entity> wrapper = Wrappers.query(model);

        //处理条件
        handlerWrapper(wrapper, params.getMap(), params);
        baseService.page(page, wrapper);
        // 处理结果
        handlerResult(page);
        return success(page);
    }

    /**
     * 自定义处理返回结果
     *
     * @param records
     */
    protected void handlerResult(IPage<Entity> records) {
        // 调用注入方法
    }

    @ApiOperation(value = "新增")
    @PostMapping
    public R<Entity> save(@RequestBody @Valid SaveDTO saveDTO) {
        R<Entity> result = handlerSave(saveDTO);
        if (result.getDefExec()) {
            Entity model = BeanUtil.toBean(saveDTO, getEntityClass());
            baseService.save(model);
            result.setData(model);
        }
        return result;
    }

    /**
     * 用户自定义新增
     *
     * @param saveDTO
     * @return 返回SUCCESS_RESPONSE, 调用默认更新, 返回其他不调用默认更新
     */
    protected R<Entity> handlerSave(SaveDTO saveDTO) {
        return R.success(true);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping
    public R<Boolean> delete(@RequestParam("ids[]") List<Id> ids) {
        R<Boolean> result = handlerDelete(ids);
        if (result.getDefExec()) {
            baseService.removeByIds(ids);
        }
        return result;
    }

    /**
     * 自定义删除
     *
     * @param ids
     * @return 返回SUCCESS_RESPONSE, 调用默认更新, 返回其他不调用默认更新
     */
    protected R<Boolean> handlerDelete(List<Id> ids) {
        return R.success(true, true);
    }

    @ApiOperation(value = "修改")
    @PutMapping
    public R<Entity> update(@RequestBody UpdateDTO updateDTO) {
        R<Entity> result = handlerUpdate(updateDTO);
        if (result.getDefExec()) {
            Entity model = BeanUtil.toBean(updateDTO, getEntityClass());
            baseService.updateById(model);
            result.setData(model);
        }
        return result;
    }

    /**
     * 自定义更新
     *
     * @param model
     * @return 返回SUCCESS_RESPONSE, 调用默认更新, 返回其他不调用默认更新
     */
    protected R<Entity> handlerUpdate(UpdateDTO model) {
        return R.success(true);
    }

    /**
     * 查询用户
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询用户", notes = "查询用户")
    @GetMapping("/{id}")
    public R<Entity> get(@PathVariable Id id) {
        return success(baseService.getById(id));
    }


    @ApiOperation(value = "导出Excel")
    @RequestMapping(value = "/export", method = RequestMethod.POST, produces = "application/octet-stream")
    public void exportExcel(@RequestBody @Validated RequestParams<PageDTO> params, HttpServletRequest request, HttpServletResponse response) throws IOException {
        IPage<Entity> page = params.getPage();
        page.setSize(Integer.MAX_VALUE);

        Entity model = BeanUtil.toBean(params.getModel(), getEntityClass());
        QueryWrapper wrapper = new QueryWrapper<>(model);
        handlerWrapper(wrapper, params.getMap(), params);
        baseService.page(page, wrapper);

        String fileName = params.getMap().getOrDefault(NormalExcelConstants.FILE_NAME, "临时文件");
        String title = params.getMap().get("title");
        String type = params.getMap().getOrDefault("type", "XSSF");

        ExcelType excelType = "XSSF".equals(type) ? ExcelType.XSSF : ExcelType.HSSF;

        ExportParams exportParams = new ExportParams(title, fileName, excelType);

        Map<String, Object> map = new HashMap<>();
        map.put(NormalExcelConstants.DATA_LIST, page.getRecords());
        map.put(NormalExcelConstants.CLASS, getEntityClass());
        map.put(NormalExcelConstants.PARAMS, exportParams);
        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }

    @ApiOperation(value = "预览Excel")
    @RequestMapping(value = "/preview", method = RequestMethod.POST)
    public R<String> preview(@RequestBody @Validated RequestParams<PageDTO> params, HttpServletRequest request) {
        IPage<Entity> page = params.getPage();
        page.setSize(Integer.MAX_VALUE);
        Entity model = BeanUtil.toBean(params.getModel(), getEntityClass());
        QueryWrapper wrapper = new QueryWrapper<>(model);
        handlerWrapper(wrapper, params.getMap(), params);
        baseService.page(page, wrapper);

        String fileName = params.getMap().getOrDefault(NormalExcelConstants.FILE_NAME, "临时文件");
        String title = params.getMap().get("title");
        String type = params.getMap().getOrDefault("type", "XSSF");

        ExcelType excelType = "XSSF".equals(type) ? ExcelType.XSSF : ExcelType.HSSF;
        ExportParams exportParams = new ExportParams(title, fileName, excelType);
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, getEntityClass(), page.getRecords());
        return success(ExcelXorHtmlUtil.excelToHtml(new ExcelToHtmlParams(workbook)));
    }

    /**
     * 使用自动生成的实体+注解方式导入 对RemoteData 类型的字段不支持，
     * 建议自建实体使用
     *
     * @param request
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "导入Excel")
    @PostMapping(value = "/import")
    public R<Boolean> importExcel(@RequestParam(value = "file") MultipartFile simpleFile, HttpServletRequest request) throws Exception {
        ImportParams params = new ImportParams();
        params.setTitleRows(0);
        params.setHeadRows(1);
        List<Map<String, String>> list = ExcelImportUtil.importExcel(simpleFile.getInputStream(), Map.class, params);

        if (list != null && !list.isEmpty()) {
            handlerImport(list);
        }
        return success();
    }

    protected void handlerImport(List<Map<String, String>> list) {

    }


}
