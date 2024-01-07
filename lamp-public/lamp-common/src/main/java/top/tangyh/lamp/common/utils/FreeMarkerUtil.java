package top.tangyh.lamp.common.utils;

import cn.hutool.crypto.digest.DigestUtil;
import freemarker.cache.MruCacheStorage;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.StringTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModelException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import top.tangyh.basic.utils.StrPool;

import java.io.StringWriter;
import java.util.Map;

/**
 * 模板引擎工具类
 *
 * @author tangyh
 * @version v1.0
 * @date 2022/7/25 12:24 PM
 * @create [2022/7/25 12:24 PM ] [tangyh] [初始创建]
 */
@Slf4j
public class FreeMarkerUtil {
    private final static Configuration FREEMARKER_CFG;
    private final static StringTemplateLoader SL;

    static {
        FREEMARKER_CFG = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        SL = new StringTemplateLoader();
        FREEMARKER_CFG.setBooleanFormat("c");
        FREEMARKER_CFG.setNumberFormat("0.##");
        generateSharedVariable();
        FREEMARKER_CFG.setCacheStorage(new MruCacheStorage(2000, Integer.MAX_VALUE));
        FREEMARKER_CFG.setTemplateUpdateDelayMilliseconds(6000000L);
        TemplateLoader[] loaders = new TemplateLoader[]{SL};
        MultiTemplateLoader mt = new MultiTemplateLoader(loaders);
        FREEMARKER_CFG.setTemplateLoader(mt);
    }

    private static void generateSharedVariable() {
        try {
            BeansWrapper wrapper = new BeansWrapper(Configuration.VERSION_2_3_30);
            TemplateHashModel staticModels = wrapper.getStaticModels();
            TemplateHashModel strPool = (TemplateHashModel) staticModels.get("top.tangyh.basic.utils.StrPool");
            FREEMARKER_CFG.setSharedVariable("StrPool", strPool);
            TemplateHashModel dateUtils = (TemplateHashModel) staticModels.get("top.tangyh.basic.utils.DateUtils");
            FREEMARKER_CFG.setSharedVariable("DateUtils", dateUtils);
            TemplateHashModel argumentAssert = (TemplateHashModel) staticModels.get("top.tangyh.basic.utils.ArgumentAssert");
            FREEMARKER_CFG.setSharedVariable("ArgumentAssert", argumentAssert);
            TemplateHashModel beanPlusUtil = (TemplateHashModel) staticModels.get("top.tangyh.basic.utils.BeanPlusUtil");
            FREEMARKER_CFG.setSharedVariable("BeanPlusUtil", beanPlusUtil);
            TemplateHashModel collHelper = (TemplateHashModel) staticModels.get("top.tangyh.basic.utils.CollHelper");
            FREEMARKER_CFG.setSharedVariable("CollHelper", collHelper);
            TemplateHashModel springUtils = (TemplateHashModel) staticModels.get("top.tangyh.basic.utils.SpringUtils");
            FREEMARKER_CFG.setSharedVariable("SpringUtils", springUtils);
            TemplateHashModel strHelper = (TemplateHashModel) staticModels.get("top.tangyh.basic.utils.StrHelper");
            FREEMARKER_CFG.setSharedVariable("StrHelper", strHelper);
            TemplateHashModel treeUtil = (TemplateHashModel) staticModels.get("top.tangyh.basic.utils.TreeUtil");
            FREEMARKER_CFG.setSharedVariable("TreeUtil", treeUtil);
        } catch (TemplateModelException e) {
            log.error(e.getMessage(), e);
        }
    }

    @SneakyThrows
    public static String generateString(String strTemplate, Map<String, Object> parameters) {
        String templateName = DigestUtil.md5Hex(strTemplate);
        if (SL.findTemplateSource(templateName) == null) {
            SL.putTemplate(templateName, strTemplate);
        }

        StringWriter writer = new StringWriter();
        Template template = FREEMARKER_CFG.getTemplate(templateName, StrPool.UTF8);
        template.process(parameters, writer);
        return writer.toString();
    }
}
