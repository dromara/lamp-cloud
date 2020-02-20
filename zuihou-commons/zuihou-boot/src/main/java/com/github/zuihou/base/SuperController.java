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
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.base.request.PageParams;
import com.github.zuihou.base.service.SuperService;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.QueryWrap;
import com.github.zuihou.exception.BizException;
import com.github.zuihou.exception.code.BaseExceptionCode;
import com.github.zuihou.log.annotation.SysLog;
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
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SuperController
 * <p>
 * 封装了如下方法：
 * 1，page 分页查询，并支持子类扩展3个方法：handlerQueryParams、query、handlerResult
 * 2，save 保存，并支持子类扩展方法：handlerSave
 * 3，update 修改，并支持子类扩展方法：handlerUpdate
 * 4，delete 删除，并支持子类扩展方法：handlerDelete
 * 5，import 导入，并支持子类扩展方法：handlerImport
 * 6，export 导出，并支持子类扩展3个方法：handlerQueryParams、query、handlerResult
 * 7，preview 导出预览，并支持子类扩展3个方法：handlerQueryParams、query、handlerResult
 * <p>
 * 其中 page、export、preview 的查询条件一致，若子类重写了 handlerQueryParams、query、handlerResult 等任意方法，均衡收到影响
 * <p>
 * 若重写扩展方法无法满足，则可以重写page、save等方法，但切记不要修改 @RequestMapping 参数
 *
 * @author Caratacus
 */
public abstract class SuperController<S extends SuperService<Entity>, Id extends Serializable, Entity, PageDTO, SaveDTO, UpdateDTO> {

    protected Class<Entity> entityClass = null;
    @Autowired
    protected S baseService;

    protected SuperController() {
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
     * 获取当前id
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


    @ApiOperation(value = "新增")
    @PostMapping
    @SysLog(value = "新增", request = false)
    public R<Entity> save(@RequestBody @Validated SaveDTO saveDTO) {
        R<Entity> result = handlerSave(saveDTO);
        if (result.getDefExec()) {
            Entity model = BeanUtil.toBean(saveDTO, getEntityClass());
            baseService.save(model);
            result.setData(model);
        }
        return result;
    }

    /**
     * 自定义新增
     *
     * @param saveDTO
     * @return 返回SUCCESS_RESPONSE, 调用默认更新, 返回其他不调用默认更新
     */
    protected R<Entity> handlerSave(SaveDTO saveDTO) {
        return R.successDef();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping
    @SysLog("'删除:' + #ids")
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
        return R.successDef();
    }

    @ApiOperation(value = "修改")
    @PutMapping
    @SysLog(value = "'修改:' + #updateDTO?.id", request = false)
    public R<Entity> update(@RequestBody @Validated(SuperEntity.Update.class) UpdateDTO updateDTO) {
        R<Entity> result = handlerUpdate(updateDTO);
        if (result.getDefExec()) {
            Entity model = BeanUtil.toBean(updateDTO, getEntityClass());
            baseService.updateAllById(model);
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
        return R.successDef();
    }

    /**
     * 查询
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询", notes = "查询")
    @GetMapping("/{id}")
    @SysLog("'查询:' + #id")
    public R<Entity> get(@PathVariable Id id) {
        return success(baseService.getByIdCache(id));
    }

    /**
     * 使用自动生成的实体+注解方式导入 对RemoteData 类型的字段不支持，
     * 建议自建实体使用
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "导入Excel")
    @PostMapping(value = "/import")
    @SysLog(value = "'导入Excel:' + #simpleFile?.originalFilename", request = false)
    public R<Boolean> importExcel(@RequestParam(value = "file") MultipartFile simpleFile) throws Exception {
        ImportParams params = new ImportParams();
        params.setTitleRows(0);
        params.setHeadRows(1);
        List<Map<String, String>> list = ExcelImportUtil.importExcel(simpleFile.getInputStream(), Map.class, params);

        if (list != null && !list.isEmpty()) {
            handlerImport(list);
        }
        return success();
    }

    //转换后保存
    protected void handlerImport(List<Map<String, String>> list) {

    }

    @ApiOperation(value = "分页列表查询")
    @PostMapping(value = "/page")
    @SysLog(value = "'分页列表查询:第' + #params?.current + '页, 显示' + #params?.size + '行'", response = false)
    public R<IPage<Entity>> page(@RequestBody @Validated PageParams<PageDTO> params) {
        // 处理参数
        IPage<Entity> page = params.getPage();
        query(params, page, null);
        return success(page);
    }

    @ApiOperation(value = "导出Excel")
    @RequestMapping(value = "/export", method = RequestMethod.POST, produces = "application/octet-stream")
    @SysLog("'导出Excel:'.concat(#params.map[" + NormalExcelConstants.FILE_NAME + "]?:'')")
    public void exportExcel(@RequestBody @Validated PageParams<PageDTO> params, HttpServletRequest request, HttpServletResponse response) throws IOException {
        IPage<Entity> page = params.getPage();
        ExportParams exportParams = getExportParams(params, page);

        Map<String, Object> map = new HashMap<>();
        map.put(NormalExcelConstants.DATA_LIST, page.getRecords());
        map.put(NormalExcelConstants.CLASS, getEntityClass());
        map.put(NormalExcelConstants.PARAMS, exportParams);
        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }

    @ApiOperation(value = "预览Excel")
    @SysLog("'预览Excel:' + (#params.map[" + NormalExcelConstants.FILE_NAME + "]?:'')")
    @RequestMapping(value = "/preview", method = RequestMethod.POST)
    public R<String> preview(@RequestBody @Validated PageParams<PageDTO> params, HttpServletRequest request) {
        IPage<Entity> page = params.getPage();
        ExportParams exportParams = getExportParams(params, page);

        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, getEntityClass(), page.getRecords());
        return success(ExcelXorHtmlUtil.excelToHtml(new ExcelToHtmlParams(workbook)));
    }

    protected ExportParams getExportParams(PageParams<PageDTO> params, IPage<Entity> page) {
        query(params, page, params.getSize() == -1 ? Convert.toLong(Integer.MAX_VALUE) : params.getSize());

        String fileName = params.getMap().getOrDefault(NormalExcelConstants.FILE_NAME, "临时文件");
        String title = params.getMap().get("title");
        String type = params.getMap().getOrDefault("type", ExcelType.XSSF.name());

        ExcelType excelType = ExcelType.XSSF.name().equals(type) ? ExcelType.XSSF : ExcelType.HSSF;
        return new ExportParams(title, fileName, excelType);
    }

    /**
     * 处理时间区间，可以覆盖后处理组装查询条件
     *
     * @param wrapper
     * @param params
     */
    protected void handlerWrapper(QueryWrap wrapper, PageParams<PageDTO> params) {
        if (CollUtil.isNotEmpty(params.getMap())) {
            Map<String, String> map = params.getMap();
            //拼装区间
            for (Map.Entry<String, String> field : map.entrySet()) {
                String key = field.getKey();
                String value = field.getValue();
                if (StrUtil.isEmpty(value)) {
                    continue;
                }
                if (key.endsWith("_st")) {
                    String beanField = StrUtil.subBefore(key, "_st", true);
                    wrapper.ge(getDbField(beanField, getEntityClass()), DateUtils.getStartTime(value));
                }
                if (key.endsWith("_ed")) {
                    String beanField = StrUtil.subBefore(key, "_ed", true);
                    wrapper.le(getDbField(beanField, getEntityClass()), DateUtils.getEndTime(value));
                }
            }
        }

    }

    /**
     * 处理参数
     *
     * @param params
     */
    protected void handlerQueryParams(PageParams<PageDTO> params) {
    }


    /**
     * 执行查询
     * <p>
     * 可以覆盖后重写查询逻辑
     *
     * @param params
     * @param page
     * @param defSize
     */
    protected void query(PageParams<PageDTO> params, IPage<Entity> page, Long defSize) {
        handlerQueryParams(params);

        if (defSize != null) {
            page.setSize(defSize);
        }
        Entity model = BeanUtil.toBean(params.getModel(), getEntityClass());
        QueryWrap wrapper = Wraps.q(model);

        handlerWrapper(wrapper, params);
        baseService.page(page, wrapper);

        // 处理结果
        handlerResult(page);
    }

    /**
     * 自定义处理返回结果
     *
     * @param page
     */
    protected void handlerResult(IPage<Entity> page) {
        // 调用注入方法
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
        if (field == null) {
            return StrUtil.EMPTY;
        }
        TableField tf = field.getAnnotation(TableField.class);
        if (tf != null && StringUtils.isNotEmpty(tf.value())) {
            String str = tf.value();
            return str;
        }
        return StrUtil.EMPTY;
    }


}
